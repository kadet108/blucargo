/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.blucargo.model.init;

import java.util.ArrayList;
import java.util.List;

import com.blucargo.model.SearchCriteria;


/**
 *
 * @author kadet
 */
public class SearchCriteriaData {
    public List<SearchCriteria> Create()
    {
            ArrayList<SearchCriteria> searchCriteria = new ArrayList<SearchCriteria>();
            
            SearchCriteria s1 = new SearchCriteria();
            s1.setCriteriaName("testCriteria");
            s1.setUserName("a");
            
            searchCriteria.add(s1);
            return searchCriteria ;
    }

}
