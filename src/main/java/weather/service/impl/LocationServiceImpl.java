package weather.service.impl;

import org.springframework.stereotype.Service;
import weather.data.Location;

@Service(value="locationService") // Пакетный доступ
class LocationServiceImpl extends AbstractService<Location,String> { }

// Вынесем в абстрактный класс общую реализацию и проверим работоспособность