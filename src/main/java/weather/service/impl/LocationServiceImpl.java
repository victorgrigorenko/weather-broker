package weather.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weather.data.Location;

@Service(value="locationService")
class LocationServiceImpl extends AbstractService<Location,String>{ }
