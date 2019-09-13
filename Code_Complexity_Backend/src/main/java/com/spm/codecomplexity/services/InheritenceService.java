package com.spm.codecomplexity.services;

import com.spm.codecomplexity.model.SingleLine;
import com.spm.codecomplexity.util.CommonConstants;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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
                line.setCi(totalInheritCount+1);
            } else if (line.getStatement().contains("extends")) {
                System.out.println("__________________________________________________________________________________________________________________________________________");
                int count = 0;
                count++;
                extendCount = extendCount + 1;
                String[] items = line.getStatement().split(",");
                totalInheritCount = totalInheritCount + count + items.length;
                System.out.println("Inheritence : " + totalInheritCount);
                System.out.println("Complexity according to Inheritence : " + (totalInheritCount + 1));
                line.setCi(totalInheritCount+1);
            } else {
                if (!isNullOrEmptyString(line.getStatement().toString())) {
                    String[] items = line.getStatement().split("\\s");
                    ArrayList<String> tempList = new ArrayList<>();
                    for (int x = 0; x < items.length; x++) {
                        if (!isNullOrEmptyString(items[x].toString())) {
                            tempList.add(items[x]);
                        }
                    }
                    if (tempList.get(0).startsWith("@") || tempList.get(0).contains("//")) {
                        line.setCi(totalInheritCount);
                    } else {
                        int tempInCount = 0;
                        for (String s : tempList) {
                            if (CommonConstants.KEYWORDS.contains(s) || s.contains("}") || s.contains("{") ||
                                    CommonConstants.METHOD_DEFINITIONS_IDENTIFIER.contains(s) ||
                                    CommonConstants.TRY_CATCT_IDENTIFIER.contains(s) || s.equals("null")) {
                                continue;
                            } else {
                                tempInCount++;
                            }
                         }
                        if(tempInCount != 0){
                            line.setCi(totalInheritCount+2);
                        }
                    }


                }else {
                    line.setCi(totalInheritCount);
                }
            }
        }
        return statementList;
    }

    public static boolean isNullOrEmptyString(String string) {
        return string == null || string.trim().equals("");
    }


}
