package ie.ait.agile.agileproject.controller;

import ie.ait.agile.agileproject.domain.Credentials;
import ie.ait.agile.agileproject.domain.Login;
import ie.ait.agile.agileproject.domain.PatientCredentials;
import ie.ait.agile.agileproject.entity.*;
import ie.ait.agile.agileproject.exception.ExceptionHandler;
import ie.ait.agile.agileproject.service.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class Practise {

    private final HseService hseService;
    private final PatientService patientService;
    private final GpService gpService;
    private final PharmacistService pharmaService;
    private final OSMService osmService;

    Practise(HseService hseService, PatientService patientService, GpService gpService, PharmacistService pharmaService, OSMService osmService) {
        this.hseService = hseService;
        this.patientService = patientService;
        this.gpService = gpService;
        this.osmService = osmService;
        this.pharmaService = pharmaService;
    }


    @GetMapping("/")
    public String hello(Model model) {

        model.addAttribute("login", new Login());
        return "index";
    }

    @PostMapping("/hseLogin")
    public String hseLogin(@Valid Login login, Model model, @ModelAttribute Credentials credentials) {
        String loginUser = login.getUsername();
        String loginPwd = login.getPassword();

        Hse hse = hseService.hseDetails(loginUser);
        String hseUser = hse.getUsername();
        String hsePwd = hse.getPassword();

        System.out.println(loginUser + " from HTML " + loginPwd);
        System.out.println(hseUser + " from DB " + hsePwd);


        if (hseUser.equals(loginUser) && hsePwd.equals(loginPwd)) {
            model.addAttribute("hseLoginComplete", true);
            model.addAttribute("credentials", credentials);
            model.addAttribute("hseUsers", hseService.findAll());
            model.addAttribute("gpUsers", gpService.findAll());
            model.addAttribute("pharmaUsers", pharmaService.findAll());
            model.addAttribute("patientUsers", patientService.findAll());
            model.addAttribute("osmUsers", osmService.findAll());
            model.addAttribute("searchHse",false);

            return "hsePage";
        } else {
            model.addAttribute("invalidDetails", true);
            return "index";
        }

    }

    @PostMapping("/gpLogin")
    public String gpLogin(@Valid Login login, Model model) {
        String loginUser = login.getUsername();
        String loginPwd = login.getPassword();
        model.addAttribute("patientUsers", patientService.findAll());
        Gp gp = gpService.details(loginUser);
        String gpUser = gp.getUsername();
        String gpPwd = gp.getPassword();

        if (gpUser.equals(loginUser) && gpPwd.equals(loginPwd)) {
            model.addAttribute("hseLoginComplete", true);
            model.addAttribute("login", login);
            model.addAttribute("patientCredentials", new PatientCredentials());
            return "gpPage";
        } else {
            model.addAttribute("invalidGpDetails", true);
            return "index";
        }

    }

    @PostMapping("/patientLogin")
    public String patientLogin(@Valid Login login, Model model) {
        String loginUser = login.getUsername();
        String loginPwd = login.getPassword();

        Patient patient = patientService.details(loginUser);
        String patientUser = patient.getUsername();
        String patientPwd = patient.getPassword();

        System.out.println(loginUser + " from HTML " + loginPwd);
        System.out.println(patientUser + " from DB " + patientPwd);

        if (patientUser.equals(loginUser) && patientPwd.equals(loginPwd)) {
            model.addAttribute("patientLoginComplete", true);
            model.addAttribute("login", login);
            model.addAttribute("patient", patient);
            return "patientPage";
        } else {
            model.addAttribute("invalidPatientDetails", true);
            return "index";
        }
    }

    @PostMapping("/pharmacistLogin")
    public String pharmacistLogin(@Valid Login login, Model model) {
        String loginUser = login.getUsername();
        String loginPwd = login.getPassword();
        model.addAttribute("patientUsers", patientService.findAll());
        Pharmacist pharma = pharmaService.details(loginUser);
        String pharmaUser = pharma.getUsername();
        String pharmaPwd = pharma.getPassword();

        System.out.println(loginUser + " from HTML " + loginPwd);
        System.out.println(pharmaUser + " from DB " + pharmaPwd);

        if (pharmaUser.equals(loginUser) && pharmaPwd.equals(loginPwd)) {
            model.addAttribute("pharmaLoginComplete", true);
//            model.addAttribute("login",  login);
           // model.addAttribute("pharmacist", pharma);
            return "pharmacistPage";
        } else {
            model.addAttribute("invalidPharmaDetails", true);
            return "index";
        }
    }

    @PostMapping("/osmLogin")
    public String osmLogin(@Valid Login login, Model model) {
        String loginUser = login.getUsername();
        String loginPwd = login.getPassword();

        OSM osm = osmService.details(loginUser);
        String osmUser = osm.getUsername();
        String osmPwd = osm.getPassword();

//        System.out.println(loginUser + " from HTML " + loginPwd);
//        System.out.println(patientUser + " from DB " + patientPwd);

        if (osmUser.equals(loginUser) && osmPwd.equals(loginPwd)) {
            model.addAttribute("osmLoginComplete", true);
            model.addAttribute("login", login);
            model.addAttribute("patientUsers", patientService.findAll());
            return "osmPage";
        } else {
            model.addAttribute("invalidOsmDetails", true);
            return "index";
        }
    }


    @PostMapping("/createAdmin")
    public String createHse(@Valid Credentials credentials, Model model) throws Exception {

        String username = credentials.getUsername();
        String password = credentials.getPassword();
        String name = credentials.getName();
        String email = credentials.getEmail();
        String badgeNo = credentials.getBadgeNo();


        Hse admin = new Hse();

        admin.setName(name);
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setEmail(email);
        admin.setBadgeNo(badgeNo);
        admin.setActive(true);


        if (hseService.findByUsername(username) != null) {
            model.addAttribute("hseUsernameExist", true);

        } else if (hseService.findByEmail(email) != null) {

            model.addAttribute("hseEmailExist", true);
        } else if (hseService.findByBadgeNo(badgeNo) != null) {
            System.out.println("Badge doesnt");
            model.addAttribute("hseBadgeExist", true);
        } else {
            model.addAttribute("credentials", new Credentials());

            System.out.println(admin.getName());
            System.out.println(admin.getUsername());
            System.out.println(admin.getPassword());
            System.out.println(admin.getEmail());
            System.out.println(admin.getBadgeNo());
            System.out.println(admin.isActive());
            model.addAttribute("hseUsers", hseService.findAll());
            model.addAttribute("gpUsers", gpService.findAll());
            model.addAttribute("pharmaUsers", pharmaService.findAll());
            model.addAttribute("patientUsers", patientService.findAll());
            model.addAttribute("osmUsers", osmService.findAll());


            hseService.createHse(admin);
            model.addAttribute("hseCreated", true);


        }


        return "hsePage";


    }


    @PostMapping("/createGp")
    public String createGp(@Valid Credentials credentials, Model model) throws Exception {

        String username = credentials.getUsername();
        String password = credentials.getPassword();
        String name = credentials.getName();
        String email = credentials.getEmail();
        String badgeNo = credentials.getBadgeNo();


        Gp gp = new Gp();
        gp.setActive(true);
        gp.setName(name);
        gp.setUsername(username);
        gp.setPassword(password);
        gp.setBadgeNo(badgeNo);
        gp.setEmail(email);

        model.addAttribute("hseUsers", hseService.findAll());
        model.addAttribute("gpUsers", gpService.findAll());
        model.addAttribute("pharmaUsers", pharmaService.findAll());
        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("osmUsers", osmService.findAll());


//        System.out.println(gp.getName());
//        System.out.println(gp.getUsername());
//        System.out.println(gp.getPassword());
//        System.out.println(gp.getEmail());
//        System.out.println(gp.getBadgeNo());
//        System.out.println(gp.isActive());


        if (gpService.findByUsername(username) != null) {
            model.addAttribute("gpUsernameExist", true);

        } else if (gpService.findByEmail(email) != null) {

            model.addAttribute("gpEmailExist", true);
        } else if (gpService.findByBadgeNo(badgeNo) != null) {
            System.out.println("Badge doesnt");
            model.addAttribute("gpBadgeExist", true);
        } else {
            model.addAttribute("credentials", new Credentials());


            hseService.createGp(gp);
            model.addAttribute("gpCreated", true);


        }


        return "hsePage";


    }


    @PostMapping("/createPharmacist")
    public String createPharmacist(@Valid Credentials credentials, Model model) throws Exception {

        String username = credentials.getUsername();
        String password = credentials.getPassword();
        String name = credentials.getName();
        String email = credentials.getEmail();
        String badgeNo = credentials.getBadgeNo();


        Pharmacist pharma = new Pharmacist();
        pharma.setActive(true);
        pharma.setBadgeNo(badgeNo);
        pharma.setUsername(username);
        pharma.setPassword(password);
        pharma.setEmail(email);
        pharma.setBadgeNo(badgeNo);
        pharma.setName(name);

        model.addAttribute("hseUsers", hseService.findAll());
        model.addAttribute("gpUsers", gpService.findAll());
        model.addAttribute("pharmaUsers", pharmaService.findAll());
        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("osmUsers", osmService.findAll());


        if (pharmaService.findByUsername(username) != null) {
            model.addAttribute("pharmaUsernameExist", true);

        } else if (pharmaService.findByEmail(email) != null) {

            model.addAttribute("pharmaEmailExist", true);
        } else if (pharmaService.findByBadgeNo(badgeNo) != null) {
//            System.out.println("Badge doesnt");
            model.addAttribute("pharmaBadgeExist", true);
        } else {
            hseService.createPharmacist(pharma);
            model.addAttribute("credentials", new Credentials());
            model.addAttribute("pharmaCreated", true);

        }


        return "hsePage";


    }


    @PostMapping("/createOsm")
    public String createOsm(@Valid Credentials credentials, Model model) throws Exception {

        String username = credentials.getUsername();
        String password = credentials.getPassword();
        String name = credentials.getName();
        String email = credentials.getEmail();
        String badgeNo = credentials.getBadgeNo();


        OSM osm = new OSM();
        osm.setActive(true);
        osm.setBadgeNo(badgeNo);
        osm.setEmail(email);
        osm.setUsername(username);
        osm.setPassword(password);
        osm.setName(name);

        model.addAttribute("hseUsers", hseService.findAll());
        model.addAttribute("gpUsers", gpService.findAll());
        model.addAttribute("pharmaUsers", pharmaService.findAll());
        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("osmUsers", osmService.findAll());


        if (osmService.findByUsername(username) != null) {
            model.addAttribute("osmUsernameExist", true);

        } else if (osmService.findByEmail(email) != null) {

            model.addAttribute("osmEmailExist", true);
        } else if (osmService.findByBadgeNo(badgeNo) != null) {
//            System.out.println("Badge doesnt");
            model.addAttribute("osmBadgeExist", true);
        } else {
            hseService.createOsm(osm);
            model.addAttribute("credentials", new Credentials());
            model.addAttribute("osmCreated", true);

        }


        return "hsePage";


    }


    ///////////////////////////////////////////////////////////////////////
    @PostMapping("/deactivateHse")
    public String deactivateHse(@ModelAttribute("credentials") Credentials credentials, Model model) throws Exception {

        hseService.deactivateHse(credentials.getBadgeNo());
        model.addAttribute("deactivatedHse", true);
        model.addAttribute("classDeactivateHse", true);
        model.addAttribute("hseUsers", hseService.findAll());
        model.addAttribute("gpUsers", gpService.findAll());
        model.addAttribute("pharmaUsers", pharmaService.findAll());
        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("osmUsers", osmService.findAll());

        return "hsePage";

    }

    @PostMapping("/deactivateGp")
    public String deactivateGp(@ModelAttribute("credentials") Credentials credentials, Model model) throws Exception {
        hseService.deactivateGp(credentials.getBadgeNo());

        model.addAttribute("deactivatedGp", true);
        model.addAttribute("credentials", new Credentials());
        model.addAttribute("classDeactivateGp", true);
        model.addAttribute("hseUsers", hseService.findAll());
        model.addAttribute("gpUsers", gpService.findAll());
        model.addAttribute("pharmaUsers", pharmaService.findAll());
        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("osmUsers", osmService.findAll());
        return "hsePage";
    }

    @PostMapping("/deactivatePatient")
    public String deactivatePatient(@ModelAttribute("credentials") Credentials credentials, Model model) throws Exception {
        hseService.deactivatePatient(credentials.getUsername());
        model.addAttribute("deactivatedPatient", true);
        model.addAttribute("classDeactivatePatient", true);
        model.addAttribute("hseUsers", hseService.findAll());
        model.addAttribute("gpUsers", gpService.findAll());
        model.addAttribute("pharmaUsers", pharmaService.findAll());
        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("osmUsers", osmService.findAll());
        return "hsePage";
    }

    @PostMapping("/deactivatePharma")
    public String deactivatePharma(@ModelAttribute("credentials") Credentials credentials, Model model) throws Exception {
        hseService.deactivatePharma(credentials.getBadgeNo());
        model.addAttribute("deactivatedPharma", true);
        model.addAttribute("classDeactivatePharma", true);
        model.addAttribute("hseUsers", hseService.findAll());
        model.addAttribute("gpUsers", gpService.findAll());
        model.addAttribute("pharmaUsers", pharmaService.findAll());
        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("osmUsers", osmService.findAll());
        return "hsePage";
    }

    @PostMapping("/deactivateOsm")
    public String deactivateOsm(@ModelAttribute("credentials") Credentials credentials, Model model) throws Exception {
        hseService.deactivateOsm(credentials.getBadgeNo());
        model.addAttribute("deactivatedOsm", true);
        model.addAttribute("classDeactivateOsm", true);
        model.addAttribute("hseUsers", hseService.findAll());
        model.addAttribute("gpUsers", gpService.findAll());
        model.addAttribute("pharmaUsers", pharmaService.findAll());
        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("osmUsers", osmService.findAll());
        return "hsePage";
    }

    @PostMapping("/createPatient")
    public String createPatient(@Valid PatientCredentials patientCredentials, Model model) throws Exception {
        Patient patient = new Patient();
        patient.setName(patientCredentials.getName());
        patient.setUsername(patientCredentials.getUsername());
        patient.setPassword(patientCredentials.getPassword());
        patient.setEmail(patientCredentials.getEmail());
        patient.setEmergencyId(patientCredentials.getEmergencyId());
        patient.setActive(true);

        model.addAttribute("patientUsers", patientService.findAll());


        Gp gp= gpService.findByUsername(patientCredentials.getGpUsername());

        if (patientService.findByUsername(patient.getUsername()) != null) {
            model.addAttribute("patientUsernameExist", true);
        } else if (patientService.findByEmail(patient.getEmail()) != null) {
            model.addAttribute("patientEmailExist", true);
        }
        else if (gp == null||gp.isActive()==false) {
            model.addAttribute("gpCreatePatientNotExist", true);
        } else {
            gpService.createPatient(patient,gp);
            model.addAttribute("patientCreated", true);
            model.addAttribute("patientCredentials", new PatientCredentials());
        }

        return "gpPage";
    }


    @PostMapping("/updatePharmaPassword")
    public String updatePharmaPassword(@ModelAttribute("oldPassword") String oldpassword, @ModelAttribute("newPassword")String newpassword, @ModelAttribute("username")String username,Model model) throws Exception {


        model.addAttribute("updatePharmaPassword",true);
        model.addAttribute("patientUsers", patientService.findAll());
        Pharmacist pharma= pharmaService.findByUsername(username);
        if(pharma==null){
            model.addAttribute("updatePasswordUsernameNotExist",true);


        }
        else{
            pharmaService.updatePassword(username,oldpassword,newpassword);
            model.addAttribute("updatePharmaPasswordSuccess",true);
            model.addAttribute("oldPassword","");
            model.addAttribute("newPassword","");

        }

        return "pharmacistPage";
    }
    @PostMapping("/updatePatientPassword")
    public String updatePatientPassword(@ModelAttribute("oldPassword") String oldpassword, @ModelAttribute("newPassword")String newpassword, @ModelAttribute("username")String username,Model model) throws Exception {


        model.addAttribute("updatePatientPassword",true);

        Patient patient= patientService.findByUsername(username);
        if(patient==null){
            model.addAttribute("updatePatientPasswordUsernameNotExist",true);


        }
        else{
            patientService.updatePassword(username,oldpassword,newpassword);
            model.addAttribute("patient",patient);
            model.addAttribute("updatePatientPasswordSuccess",true);
            model.addAttribute("oldPassword","");
            model.addAttribute("newPassword","");

        }

        return "patientPage";
    }

    @PostMapping("/updateGpPassword")
    public String updateGpPassword(@ModelAttribute("oldPassword") String oldpassword, @ModelAttribute("newPassword")String newpassword, @ModelAttribute("username")String username,Model model) throws Exception {


        model.addAttribute("updateGpPassword",true);
        model.addAttribute("patientUsers", patientService.findAll());

        Gp gp= gpService.findByUsername(username);
        if(gp==null){
            model.addAttribute("updateGpPasswordUsernameNotExist",true);


        }
        else{
            gpService.updatePassword(username,oldpassword,newpassword);
            model.addAttribute("gp",gp);
            model.addAttribute("updateGpPasswordSuccess",true);
            model.addAttribute("oldPassword","");
            model.addAttribute("newPassword","");
            model.addAttribute("patientCredentials", new PatientCredentials());
        }

        return "gpPage";
    }



    @PostMapping("/hseUpdateUser")
    public String hseUpdateUser(@ModelAttribute("hseUsername") String username, @ModelAttribute("hseName")String name, @ModelAttribute("hseEmail")String email,Model model) throws Exception {

        model.addAttribute("hseUsers", hseService.findAll());
        model.addAttribute("gpUsers", gpService.findAll());
        model.addAttribute("pharmaUsers", pharmaService.findAll());
        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("osmUsers", osmService.findAll());
        model.addAttribute("credentials",new Credentials());
        model.addAttribute("hseUpdateUser",true);


       Hse hse= hseService.findByUsername(username);
        if(hse==null||!hse.isActive()){
            model.addAttribute("hseUpdateUserUsernameNotExist",true);


        }
        else if(hse.getEmail().toLowerCase().equals(email.toLowerCase())||hseService.findByEmail(email)!=null){
            model.addAttribute("hseUpdateUserEmailExist",true);        }
        else{
            hseService.hseUpdateUser(username,name,email);

            model.addAttribute("hseEmail","");
            model.addAttribute("hseName","");
            model.addAttribute("hseUpdateUserSuccess",true);

        }

        return "hsePage";
    }
    @PostMapping("/gpUpdateUser")
    public String gpUpdateUser(@ModelAttribute("gpUsername") String username, @ModelAttribute("gpName")String name, @ModelAttribute("gpEmail")String email,Model model) throws Exception {

        model.addAttribute("hseUsers", hseService.findAll());
        model.addAttribute("gpUsers", gpService.findAll());
        model.addAttribute("pharmaUsers", pharmaService.findAll());
        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("osmUsers", osmService.findAll());
        model.addAttribute("credentials",new Credentials());
        model.addAttribute("gpUpdateUser",true);


        Gp gp= gpService.findByUsername(username);
        if(gp==null||!gp.isActive()){
            model.addAttribute("gpUpdateUserUsernameNotExist",true);


        }
        else if(gp.getEmail().toLowerCase().equals(email.toLowerCase())||gpService.findByEmail(email)!=null){
            model.addAttribute("gpUpdateUserEmailExist",true);        }
        else{
            hseService.gpUpdateUser(username,name,email);

            model.addAttribute("gpEmail","");
            model.addAttribute("gpName","");
            model.addAttribute("gpUpdateUserSuccess",true);

        }

        return "hsePage";
    }
    
    @PostMapping("/pharmaUpdateUser")
    public String pharmaUpdateUser(@ModelAttribute("pharmaUsername") String username, @ModelAttribute("pharmaName")String name, @ModelAttribute("pharmaEmail")String email,Model model) throws Exception {

        model.addAttribute("hseUsers", hseService.findAll());
        model.addAttribute("gpUsers", gpService.findAll());
        model.addAttribute("pharmaUsers", pharmaService.findAll());
        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("osmUsers", osmService.findAll());
        model.addAttribute("credentials",new Credentials());
        model.addAttribute("pharmaUpdateUser",true);


        Pharmacist pharma= pharmaService.findByUsername(username);
        if(pharma==null||!pharma.isActive()){
            model.addAttribute("pharmaUpdateUserUsernameNotExist",true);


        }
        else if(pharma.getEmail().toLowerCase().equals(email.toLowerCase())||pharmaService.findByEmail(email)!=null){
            model.addAttribute("pharmaUpdateUserEmailExist",true);        }
        else{
            hseService.pharmaUpdateUser(username,name,email);

            model.addAttribute("pharmaEmail","");
            model.addAttribute("pharmaName","");
            model.addAttribute("pharmaUpdateUserSuccess",true);

        }

        return "hsePage";
    }
    @PostMapping("/patientUpdateUser")
    public String patientUpdateUser(@ModelAttribute("patientUsername") String username, @ModelAttribute("patientName")String name, @ModelAttribute("patientEmail")String email,Model model) throws Exception {

        model.addAttribute("hseUsers", hseService.findAll());
        model.addAttribute("gpUsers", gpService.findAll());
        model.addAttribute("pharmaUsers", pharmaService.findAll());
        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("osmUsers", osmService.findAll());
        model.addAttribute("credentials",new Credentials());
        model.addAttribute("patientUpdateUser",true);


        Patient patient= patientService.findByUsername(username);
        if(patient==null||!patient.isActive()){
            model.addAttribute("patientUpdateUserUsernameNotExist",true);


        }
        else if(patient.getEmail().toLowerCase().equals(email.toLowerCase())||patientService.findByEmail(email)!=null){
            model.addAttribute("patientUpdateUserEmailExist",true);        }
        else{
            hseService.patientUpdateUser(username,name,email);

            model.addAttribute("patientEmail","");
            model.addAttribute("patientName","");
            model.addAttribute("patientUpdateUserSuccess",true);

        }

        return "hsePage";
    }
    @PostMapping("/osmUpdateUser")
    public String osmUpdateUser(@ModelAttribute("osmUsername") String username, @ModelAttribute("osmName")String name, @ModelAttribute("osmEmail")String email,Model model) throws Exception {

        model.addAttribute("hseUsers", hseService.findAll());
        model.addAttribute("gpUsers", gpService.findAll());
        model.addAttribute("pharmaUsers", pharmaService.findAll());
        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("osmUsers", osmService.findAll());
        model.addAttribute("credentials",new Credentials());
        model.addAttribute("osmUpdateUser",true);


        OSM osm= osmService.findByUsername(username);
        if(osm==null||!osm.isActive()){
            model.addAttribute("osmUpdateUserUsernameNotExist",true);


        }
        else if(osm.getEmail().toLowerCase().equals(email.toLowerCase())||osmService.findByEmail(email)!=null){
            model.addAttribute("osmUpdateUserEmailExist",true);        }
        else{
            hseService.osmUpdateUser(username,name,email);

            model.addAttribute("osmEmail","");
            model.addAttribute("osmName","");
            model.addAttribute("osmUpdateUserSuccess",true);

        }

        return "hsePage";
    }


    @PostMapping("/createPrescription")
    public String createPrescription(@ModelAttribute("patientUsername") String patientUsername, @ModelAttribute("pDate") String pDate, @ModelAttribute("prescriptionDescription")String description, Model model) throws Exception {


            //System.out.println(pDate);
        Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(pDate.replaceAll("-","/"));

        model.addAttribute("patientUsers", patientService.findAll());
        model.addAttribute("createPrescription", true);

        model.addAttribute("patientCredentials", new PatientCredentials());
        Patient patient= patientService.findByUsername(patientUsername);



        if(patient==null){
            model.addAttribute("patientNotExistCreatePrescription",true);


        }
        else{

            patientService.createPrescription(patient,description,date1);
            model.addAttribute("prescriptionDescription","");
            model.addAttribute("createPrescriptionSuccess",true);


        }

        return "gpPage";
    }
    
    
    @PostMapping("/getPrescription")
    public String getPrescription(@ModelAttribute("patientUsername") String username,  Model model) throws Exception {
        Patient patient= patientService.findByUsername(username);
//        model.addAttribute("patient", patient);
        if(patient==null){
            model.addAttribute("getPatientNotExist",true);
            return "patientPage";
        }
        else{
            List<Prescription> pre= patientService.findAllPrescription(username);
            for(Prescription pres:pre){
                System.out.println(pres.getPatient().getName());
                System.out.println(pres.getDate());
                System.out.println(pres.getDescription());
            }
            model.addAttribute("allPrescriptions",pre);
            return "prescriptionPage";
        }
    }




}
