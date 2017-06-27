package spring.organizer.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;

/**
 * Created by radu on 27.06.2017.
 */
@Converter
public class DurationToStringConverter implements AttributeConverter<Duration, String>
{
    @Override
    public String convertToDatabaseColumn(Duration duration)
    {
        return duration == null ? null : duration.toString();
    }

    @Override
    public Duration convertToEntityAttribute(String dbData)
    {
        return dbData == null ? null : Duration.parse(dbData);
    }
}

