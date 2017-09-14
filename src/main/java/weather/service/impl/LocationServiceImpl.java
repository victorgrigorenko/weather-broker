package weather.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weather.dao.AbstarctableDao;
import weather.data.Location;

@Service(value="locationService")
public class LocationServiceImpl extends AbstractService<Location,String>{
    @Transactional
    public Location get(String id){
        Location instance = abstractDao.get(id);
        return  instance;
    }

    public LocationServiceImpl(){}

    public LocationServiceImpl(AbstarctableDao<Location,String> abstarctableDao){
        this.abstractDao = abstarctableDao;
    }

}
