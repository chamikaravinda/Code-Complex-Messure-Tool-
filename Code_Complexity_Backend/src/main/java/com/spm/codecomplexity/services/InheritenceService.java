package com.spm.codecomplexity.services;

import com.spm.codecomplexity.model.SingleLine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InheritenceService {
    List<SingleLine> statementList;

    public List<SingleLine> calculateComplexityDueToInheritenceStructures(List<SingleLine> statementList) {
        this.statementList = statementList;

        for (SingleLine line : statementList) {

            int extendCount = 0;
            int totalInheritCount = 0;
            if (line.getStatement().contains("extends") && line.getStatement().contains("implements")) {
                System.out.println("__________________________________________________________________________________________________________________________________________");
                int count = 0;
                count++;
                extendCount = extendCount + 1;
                String[] items = line.getStatement().split(",");
                totalInheritCount = totalInheritCount + count + items.length;
                System.out.println("Inheritence : " + totalInheritCount);
                System.out.println("Complexity according to Inheritence : " + (totalInheritCount + 1));
            }
            line.setCi(totalInheritCount);
        }
        return statementList;
    }

}
