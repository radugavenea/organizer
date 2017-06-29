package spring.organizer.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by radu on 29.06.2017.
 */
@Convert
public class LocalDateTimeToSqlTimeConverter implements AttributeConverter<LocalDateTime, Timestamp>{

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime)
    {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData)
    {
        return dbData == null ? null : dbData.toLocalDateTime();
    }
}
