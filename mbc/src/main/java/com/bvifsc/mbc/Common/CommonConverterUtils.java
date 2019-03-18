package com.bvifsc.mbc.Common;

import com.bvifsc.mbc.network.response.signUp.SecurityQuestionMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramesh on 10-12-2018.
 */
public class CommonConverterUtils {

   public static  List<String> convert(List<SecurityQuestionMap> securityQuestionMapList)
    {
            List<String> securityQuestionsList = new ArrayList<>();
            securityQuestionsList.add(0,"Select Security Question" );
             int i=0;
            for(SecurityQuestionMap securityQuestionMap : securityQuestionMapList)
            {

                if(securityQuestionMap.getSecurityQuestionId() > 0 && securityQuestionMap.getQuestionDesc() != null) {
                        i=i+1;
                      securityQuestionsList.add(i,securityQuestionMap.getQuestionDesc());

                }

            }

        return securityQuestionsList;
    }
}
