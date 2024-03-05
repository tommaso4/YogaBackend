package com.YogApp.app.model.request;

import com.YogApp.app.model.enums.Drishtis;
import com.YogApp.app.model.enums.TypeAsana;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AsanaReq {

    @NotNull(message = "id required")
    private Integer id;
    @NotBlank(message = "name Required")
    private String name;
    @NotBlank(message = "description required")
    private String description;
    @NotNull(message = "drishti required")
    private Drishtis drishti;
    @NotNull(message = "typeAsana required")
    private TypeAsana typeAsana;
    @NotBlank(message = "benefit required")
    private String benefit;
    @NotBlank(message = "urlImg required")
    private String urlImg;
}
