package com.mazowiecka.demo.Mapper;

import com.mazowiecka.demo.DTO.TaskDTO;
import com.mazowiecka.demo.Entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDTO toDTO(Task task);
    Task toEntity(TaskDTO taskDTO);
}
