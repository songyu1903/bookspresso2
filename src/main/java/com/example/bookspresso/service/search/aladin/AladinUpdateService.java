package com.example.bookspresso.service.search.aladin;

import com.example.bookspresso.api.Aladen.Aladinitem;
import com.example.bookspresso.mapper.search.aladin.AladenUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AladinUpdateService {
    private final AladenUpdateMapper aladenUpdateMapper;
    void addAladinUpdate(Aladinitem aladinitem){aladenUpdateMapper.insertBook(aladinitem);}
    String findAladin(String isbn13){return aladenUpdateMapper.selectBooknumber(isbn13);}
}
