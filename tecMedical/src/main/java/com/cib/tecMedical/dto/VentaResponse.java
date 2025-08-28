package com.cib.tecMedical.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaResponse {

    private Integer idVenta;
    private String cliente;
    private String vendedor;
    private LocalDateTime fecha;
    private Double total;
    private List<DetalleVentaResponse> detalles;
}
