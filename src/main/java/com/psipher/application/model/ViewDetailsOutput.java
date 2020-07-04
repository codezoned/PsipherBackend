package com.psipher.application.model;

import com.psipher.application.ddbmodel.WebsiteDDBModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewDetailsOutput {
    private String userId;
    private List<WebsiteDDBModel> websites;
    private String status;
}
