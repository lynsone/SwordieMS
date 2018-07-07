package net.swordie.ms.connection.db;

import net.swordie.ms.util.FileTime;

import javax.persistence.AttributeConverter;

/**
 * @author Sjonnie
 * Created on 7/7/2018.
 */
public class FileTimeConverter implements AttributeConverter<FileTime, Long> {
    @Override
    public Long convertToDatabaseColumn(FileTime fileTime) {
        return fileTime.toLong();
    }

    @Override
    public FileTime convertToEntityAttribute(Long aLong) {
        return aLong == null ? null : new FileTime(aLong);
    }
}
