package com.giustini.codechallenge.services;

import com.giustini.codechallenge.models.Dummy;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.singletonList;

@Service
public class DummyService {

    public List<Dummy> getDummies() {
        return singletonList(new Dummy());
    }
}
