package clamos.io.dashboard.service;

import clamos.io.dashboard.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService{

    private final SchoolRepository repository;

    @Override
    public void getSumSchool() {

        List<Integer[]> result = repository.Chart_1();
        for (Integer[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }

    }

    @Override
    public List<Integer> getYear() {
        return null;
    }

    @Override
    public List<Integer> getYu() {
        return null;
    }

    @Override
    public List<Integer> getEle() {
        return null;
    }

    @Override
    public List<Integer> getMid() {
        return null;
    }

    @Override
    public List<Integer> getHigh() {
        return null;
    }

}
