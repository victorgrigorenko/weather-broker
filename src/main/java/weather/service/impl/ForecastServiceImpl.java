package weather.service.impl;

import org.springframework.stereotype.Service;
import weather.data.Forecast;

@Service(value="forecastService") // Пакетный доступ
class ForecastServiceImpl extends AbstractService<Forecast,Integer> { }
