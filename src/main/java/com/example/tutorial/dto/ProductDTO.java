package com.example.tutorial.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

  private Long id;

  @NotBlank
  @Size(max = 100)
  private String name;

  @NotBlank private String description;

  @NotNull
  @DecimalMin(value = "0.0", inclusive = false)
  private BigDecimal price;

  @NotNull
  @Min(0)
  private Integer stock;

  private Boolean active;
}
