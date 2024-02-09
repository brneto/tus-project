package ie.ait.agile.agileproject.service.impl;

import ie.ait.agile.agileproject.entity.OSM;
import ie.ait.agile.agileproject.exception.ExceptionHandler;
import ie.ait.agile.agileproject.repository.OSMRepository;
import ie.ait.agile.agileproject.service.OSMService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OSMServiceImpl implements OSMService {
    private final OSMRepository osmRepository;

    OSMServiceImpl(OSMRepository osmRepository) {

        this.osmRepository = osmRepository;
    }

    @Override
    public OSM details(String username) {


        if (osmRepository.findByUsername(username) == null) {
            throw new ExceptionHandler("Other staff member does not exist");
        } else {
            OSM osm= osmRepository.findByUsername(username);
            if(osm.isActive()==false){
                throw new ExceptionHandler("Other staff member has been deactivated");
            }
            else{
            return osmRepository.findByUsername(username);}
        }


    }

    @Override
    public OSM findByUsername(String Username) {
        return osmRepository.findByUsername(Username);
    }

    @Override
    public OSM findByEmail(String email) {
        return osmRepository.findByEmail(email);
    }

    @Override
    public OSM findByBadgeNo(String badge) {
        return osmRepository.findByBadgeNo(badge);
    }

    @Override
    public List<OSM> findAll() {
        return osmRepository.findAll();
    }
}
