/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedropasqualinifeijo.projetointegrador3.model;

import java.time.LocalDateTime;

/**
 *
 * @author User
 */
public class LancamentoReceita {
    
    private Integer id;
    private LocalDateTime data;
    private Usuarios usuario = new Usuarios();
    private TipoReceita tipoReceita = new TipoReceita();
    private double valor;
}
