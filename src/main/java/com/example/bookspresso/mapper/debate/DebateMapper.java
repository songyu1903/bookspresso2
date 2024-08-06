package com.example.bookspresso.mapper.debate;

import com.example.bookspresso.dto.debate.DebateInpoDTO;
import com.example.bookspresso.dto.debate.DebateTypeDTO;
import com.example.bookspresso.dto.debate.MettingTypeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DebateMapper {
    //토론정보 입력(토론만들기)
    void insertDebateInpo(DebateInpoDTO debateinpoDTO);

    void insertDebateType(DebateTypeDTO debateTypeDTO);
    void insertMettingType(MettingTypeDTO mettingTypeDTO);
    List<DebateInpoDTO> selectdebateinpo(Long debateId);

}