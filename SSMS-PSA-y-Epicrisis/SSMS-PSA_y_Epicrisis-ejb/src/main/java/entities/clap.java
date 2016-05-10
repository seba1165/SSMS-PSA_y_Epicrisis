/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 *
 * @author obi
 */
@Entity
public class clap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo_clap")
    private Long id;
    
    //Ficha personal de paciente
    
    @NotNull(message="Debe ingresar un nombre")
    @Column(name="nombres_clap",length=50)
    private String nombres;
    
    @NotNull(message="Debe ingresar el primer apellido")
    @Column(name="primer_apellido_clap",length=30)
    private String primer_apellido;
    
    @NotNull(message="Debe ingresar el segundo apellido")
    @Column(name="segundo_apellido_clap",length=30)
    private String segundo_apellido;
    
    @NotNull(message="Debe ingresar un nombre social o apodo")
    @Column(name="nombre_social_clap")
    private String nombre_social;
    
    @ManyToOne
    @NotNull(message="Debe ingresar una región de residencia")    
    @JoinColumn(name="region_residencia_clap")
    private region region_residencia;
    
    @ManyToOne
    @NotNull(message="Debe ingresar una comuna de residencia")
    @JoinColumn(name="comuna_residencia_clap")
    private comuna comuna_residencia;
    
    @NotNull(message="Debe ingresar una calle o avenida o pasaje de residencia")
    @Column(name="calle_direccion_clap",length = 50)
    private String calle_direccion;
    
    @NotNull(message="Debe ingresar el numero de dirección de residencia")
    @Column(name="numero_direccion_clap",length = 8)
    private String numero_direccion;
    
    @Column(name="resto_direccion_clap",length = 50)
    private String resto_direccion;
    
    @Temporal(TemporalType.DATE)
    @NotNull(message="Debe ingresar una fecha de nacimiento")
    @Column(name="fecha_nacimiento_clap")
    private java.util.Date fecha_nacimiento;
    
    @NotNull(message="Debe ingresar un run")
    @Column(name="run_clap",length=8, unique=true)
    private int RUN;
    
    @NotNull(message="Debe ingresar un dígito verificador")
    @Column(name="dv_clap",length=1)
    private String DV;

    @ManyToOne
    @JoinColumn(name="cesfam_clap")
    private cesfam cesfam;      
    
    @Column(name="telefono_fijo_clap")
    private String telefono_fijo;
    
    @Column(name="telefono_movil_clap")
    private String teléfono_movil;  
    
    @NotNull(message="Debe ingresar un sexo")
    @Column(name="sexo_clap")
    private int sexo;    
    
    @ManyToOne
    @JoinColumn(name="programa_social_clap")
    private ley_social programa_social;
    
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "Debe ser un mail valido")
    @NotNull(message="Debe ingresar un correo de contacto")
    @Column(name="correo_clap")
    private String correo;
    
    @ManyToOne
    @NotNull(message="Debe ingresar un tipo de previsión")
    @JoinColumn(name="prevision_clap")
    private prevision prevision;
    
    @Column(name="grupo_fonasa_clap")
    private int grupo_fonasa;    
    
    @ManyToOne
    @NotNull(message="Debe ingresar una nacionalidad")
    @JoinColumn(name="nacionalidad_clap")
    private nacionalidad nacionalidad;
    
    @ManyToOne
    @JoinColumn(name="estado_conyugal_clap")
    private estado_civil estado_conyugal;
    
    @ManyToOne
    @JoinColumn(name="pueblo_originario_clap")
    private pueblo_originario pueblo_originario;
    
    //Datos de la consulta
    
    @NotNull
    @Column(name="numero_consulta_clap")
    private Long numero_consulta;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_consulta_clap")
    private java.util.Date fecha_consulta;

    @NotNull
    @Column(name="edad_clap")
    private int edad;
    
    @Column(name="acompanante_clap")
    private String acompañante;
    
    @Column(name="motivo_consulta_adolescente_1_clap",length = 100)
    private String motivo_consulta_adolescente_1;
    
    @Column(name="motivo_consulta_adolescente_2_clap",length = 100)
    private String motivo_consulta_adolescente_2;
        
    @Column(name="motivo_consulta_adolescente_3_clap",length = 100)
    private String motivo_consulta_adolescente_3;
    
    @Column(name="motivo_consulta_acompanantes_1_clap",length = 100)
    private String motivo_consulta_acompañante_1;
    
    @Column(name="motivo_consulta_acompanante_2_clap",length = 100)
    private String motivo_consulta_acompañante_2;
        
    @Column(name="motivo_consulta_acompanante_3_clap",length = 100)
    private String motivo_consulta_acompañante_3;
    
    @Column(name="descripcion_motivo_consulta_clap",length = 100,columnDefinition = "TEXT")
    private String descripcion_motivo_consulta;
    
    //Antecedentes personales
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="perinatales_normales_clap",length = 1)
    private int perinatales_normales;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="alergias_clap",length = 1)
    private int alergias_normales;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="vacunas_completas_clap",length = 1)
    private int vacunas_completas;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="enfermedades_importantes_clap",length = 1)
    private int enfermedades_importantes;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="discapacidad_clap",length = 1)
    private int discapacidad;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="accidentes_relevantes_clap",length = 1)
    private int accidentes_relevantes;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="cirugia_hospitalizaciones_clap",length = 1)
    private int cirugia_hospitalizaciones;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="uso_medicamentos_clap",length = 1)
    private int uso_medicamentos;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="problemas_salud_mental_clap",length = 1)
    private int problemas_salud_mental;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="violencia_clap",length = 1)
    private int violencia;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="antecedentes_judiciales_clap",length = 1)
    private int antecedentes_judiciales;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="otros_antecedentes_personales_clap",length = 1)
    private int otros;
    
    @Column(name="observaciones_antecedentes_personales_clap",columnDefinition = "TEXT")
    private String observaciones_antecdentes_personales;
    
    //Antecedentes familiares
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="enfermeades_importantes_familia_clap",length = 1)
    private int enfermedades_importantes_familia;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="obesidad_familia_clap",length = 1)
    private int obesidad_familia;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="problemas_salud_mental_familia_clap",length = 1)
    private int problemas_salud_mental_familia;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="violencia_intrafamiliar_clap",length = 1)
    private int violencia_intrafamiliar;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="alcohol_y_otras_drogas_clap",length = 1)
    private int alcohol_y_otras_drogas;
     
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="padre_adolescente_clap",length = 1)
    private int padre_adolescente;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="judiciales_clap",length = 1)
    private int judiciales;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="otros_antecedentes_familiares_clap",length = 1)
    private int otros_antecedentes_familiares;
    
    @Column(name="observaciones_antecedentes_familiares_clap",columnDefinition = "TEXT")
    private String observaciones_antecedentes_familiares;

    //Familia
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="vive_con_solo_clap",length = 1)
    private boolean vive_con_solo;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="vive_con_madre_clap",length = 1)
    private boolean vive_con_madre;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="vive_con_padre_clap",length = 1)
    private boolean vive_con_padre;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="vive_en_institucion_clap",length = 1)
    private boolean vive_en_institucion;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="vive_con_otros_clap",length = 1)
    private boolean vive_con_otros;
    
    @Column(name="vive_con_especificación_clap")
    private String vive_con_especificación;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="comparte_cama_clap")
    private boolean comparte_cama;
    
    @Column(name="especificacion_comparte_cama_clap")
    private String especificacion_comparte_cama;
    
    @Column(name="nivel_instruccion_madre_clap",length = 1)
    private int nivel_instruccion_madre;
    
    @Column(name="nivel_instruccion_padre_clap",length = 1)
    private int nivel_instruccion_padre;
    
    @Column(name="nivel_instruccion_pareja_clap",length = 1)
    private int nivel_instruccion_pareja;
    
    @Column(name="ocupacion_madre_clap")
    private String ocupacion_madre;
    
    @Column(name="ocupacion_padre_clap")
    private String ocupacion_padre;
    
    @Column(name="ocupacion_pareja_clap")
    private String ocupacion_pareja;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="percepcion_familia_clap")
    private int percepcion_familia;
    
    //Se debe ver como poner imagenes
    @Column(name="diagrama_familiar_clap")
    private String diagrama_familiar;
    
    @Column(name="observaciones_familia_clap",columnDefinition = "TEXT")
    private String observaciones_familia;
    
    //vivienda
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="condiciones_sanitarias_clap")
    private boolean condiciones_sanitarias;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="hacinamiento_clap")
    private boolean hacinamiento;
    
    @Column(name="observaciones_vivienda_clap",columnDefinition = "TEXT")
    private String observaciones_vivienda;
    
    //Educacion
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="estudia_clap")
    private boolean estudia;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="nivel_educacion_clap")
    private int nivel_educacion;
    
    @NotNull(message = "Debe ingresar el nivel curso en que se encuentra actualmente")
    @Column(name="curso_clap")
    private String curso;
    
    @NotNull(message = "Debe debe ingresar un valor.")
    @Column(name="anos_repetidos_clap")
    private int años_repetidos;
    
    @Column(name="causa_anos_repetidos_clap")
    private String causa_años_repetidos;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="problemas_escuela_clap")
    private boolean problemas_escuela;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="violencia_escolar_clap")
    private boolean violencia_escolar;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="desercion_exclusion_clap")
    private boolean desercion_exclusion;
    
    @Column(name="causa_desercion_exclusion_clap")
    private String causa_desercion_exclusion;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="percepcion_rendimiento_clap")
    private int percepcion_rendimiento;
    
    @Column(name="observaciones_educacion_clap",columnDefinition = "TEXT")
    private String observaciones_educacion;
    
    //Trabajo
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="trabaja_clap")
    private boolean trabaja;
    
    @Column(name="horas_trabajo_clap")
    private int horas_trabajo;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="trabajo_infantil_clap")
    private boolean trabajo_infantil;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="trabajo_juvenil_clap")
    private boolean trabajo_juvenil;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="peores_formas_clap")
    private boolean peores_formas;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="ser_dom_no_remu_peligroso_clap")
    private boolean ser_dom_no_remu_peligroso;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="razon_de_trabajo_clap")
    private int razon_de_trabajo;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="legalizado_clap")
    private int legalizado;
    
    @Column(name="tipo_de_trabajo_clap")
    private String tipo_de_trabajo;
    
    @Column(name="observaciones_trabajo_clap",columnDefinition = "TEXT")
    private String observaciones_trabajo;
    
    //vida social
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="aceptacion_clap")
    private int aceptacion;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="pareja_clap")
    private boolean pareja;
    
    @Column(name="edad_pareja_clap")
    private int edad_pareja;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="violencia_pareja_clap")
    private boolean violencia_pareja;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="amigos_clap")
    private boolean amigos;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="suicidalidad_amigos_clap")
    private boolean suicidalidad_amigos;
    
    @NotNull(message = "Debe ingresar la cantidad de horas que dedica a alguna actividad física.")
    @Column(name="horas_actividad_fisica_clap")
    private int horas_actividad_fisica;
    
    @NotNull(message = "Debe ingresar la cantidad de horas que dedica a la televisión.")
    @Column(name="horas_tv_clap")
    private int horas_tv;
    
    @NotNull(message = "Debe ingresar la cantidad de horas que dedica al computador o consola.")
    @Column(name="horas_computador_consola_clap")
    private int horas_computador_consola;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="cyberbulling_clap")
    private boolean cyberbulling;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="grooming_clap")
    private boolean grooming;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="otras_actividades_clap")
    private boolean otras_actividades;
    
    @Column(name="especificacion_otras_actividades_clap")
    private String especificacion_otras_actividades;
    
    @Column(name="observaciones_vida_social_clap",columnDefinition = "TEXT")
    private String observaciones_vida_social;
    
    //habitos y consumo
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="sueno_normal_clap")
    private boolean sueño_normal;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="horas_sueno_clap")
    private int horas_sueño;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="alimentacion_adecuada_clap")
    private boolean alimentacion_adecuada;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="comidas_familia_clap")
    private int comidas_familia;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="alimentacion_especial_clap")
    private boolean alimentacion_especial;
    
    @Column(name="espeficicacion_alimentacion_especial_clap")
    private String especificacion_alimentacion_especial;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="tabaco_clap")
    private boolean tabaco;
    
    @Column(name="cigarros_dia_clap")
    private int cigarros_dia;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="consumo_alcohol_clap")
    private boolean consumo_alcohol;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="consumo_marihuana_clap")
    private boolean consumo_marihuana;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="consumo_otra_sustancia_clap")
    private boolean consumo_otra_sustancia;
 
    @Column(name="especificacion_consumo_otra_sustancia_clap")
    private boolean especificacion_consumo_otra_sustancia;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="seguridad_vial_clap")
    private boolean seguridad_vial;
    
    @Column(name="observaciones_habitos_consumo_clap",columnDefinition = "TEXT")
    private String observaciones_habitos_consumo;
    
    //gineco/urólogo
    
    @NotNull(message = "ingresar la edad de la menarca o espermarca")
    @Column(name="edad_menarca_espermarca_clap")
    private String edad_menarca_espermarca;

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_ultima_menstruacion_clap")
    private java.util.Date fecha_ultima_menstruacion;
    
    @Column(name="no_conoce_fecha_ultima_menstruacion_clap")
    private boolean no_conoce_fecha_ultima_menstruacion;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="ciclos_regulares_clap")
    private int ciclos_regulares;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="dismenorrea_clap")
    private int dismenorrea;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="flujo_secrecion_patologico_clap")
    private boolean flujo_secrecion_patologico;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="its_vih_clap")
    private boolean its_vih;
    
    @Column(name="especificacion_its_vih_clap")
    private String especificacion_its_vih;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="tratamiento_clap")
    private int tratamiento;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="tratamiento_contactos_clap")
    private int tratamiento_contactos;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="embarazos_clap")
    private int embarazos;
    
    @NotNull(message = "Debe ingresar un valor.")
    @Column(name="hijos_clap")
    private int hijos;
    
    @NotNull(message = "Debe ingresar un valor.")
    @Column(name="abortos_clap")
    private int abortos;
    
    @Column(name="observaciones_gineco_urologico_clap",columnDefinition = "TEXT")
    private String observaciones_gineco_urologico;
    
    //sexualidad
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="orientacion_sexual_clap")
    private int orientacion_sexual;
    
    @Column(name="especificacion_orientacion_sexual_clap")
    private String especificacion_orientacion_sexual;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="conducta_sexual_clap")
    private int conducta_sexual;
    
    @Column(name="edad_inicio_conducta_sexual_clap")
    private String edad_inicio_conducta_sexual;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="relaciones_sexuales_clap")
    private int relaciones_sexuales;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="pareja_sexual_clap")
    private int pareja_sexual;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="dificultades_sexuales_clap")
    private int dificultades_sexuales;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="anticoncepcion_clap")
    private int anticoncepcion;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="uso_mac_clap")
    private int uso_mac;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="especificacion_uso_mac_clap")
    private String especificacion_uso_mac;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="razon_no_uso_mac_clap")
    private String razon_no_uso_mac;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="consejera_uso_mac_clap")
    private boolean consejeria_uso_mac;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="aco_emergencia_clap")
    private boolean aco_emergencia;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="abuso_sexual_clap")
    private boolean abuso_sexual;
    
    @Column(name="reparacion_abuso_sexual_clap")
    private boolean reparacion_abuso_sexual;
    
    @Column(name="observaciones_sexualidad_clap",columnDefinition = "TEXT")
    private String observaciones_sexualidad;
    
    //Situación psico_emocinoal
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="imagen_corporal_clap")
    private int imagen_corporal_mac;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="bienestar_emocional_clap")
    private int bienestas_emocional_mac;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="vida_proyecto_clap")
    private int vida_proyecto_mac;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="ideacion_suicida_clap")
    private boolean ideacion_suicida_mac;

    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="intento_suicida_clap")
    private boolean intento_suicida_mac;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="referente_adulto_clap")
    private int referente_adulto_mac;
    
    @Column(name="nombre_referente_adulto_clap")
    private String nombre_referente_adulto_mac;
    
    @Column(name="telefono_referente_adulto_clap")
    private String telefono_referente_adulto_mac;
    
    @Column(name="observaciones_psico_emocional_clap",columnDefinition = "TEXT")
    private String observaciones_psico_emocional;
    
    //Examen físico
    
    @NotNull(message = "Debe ingresar un valor.")
    @Column(name="peso_clap")
    private int peso;
    
    @NotNull(message = "Debe ingresar un valor.")
    @Column(name="de_peso_clap")
    private int de_peso;
    
    @NotNull(message = "Debe ingresar un valor.")
    @Column(name="talla_clap")
    private int talla;
    
    @NotNull(message = "Debe ingresar un valor.")
    @Column(name="de_talla_clap")
    private int de_talla;
    
    @NotNull(message = "Debe ingresar un valor.")
    @Column(name="presion_abdominal_clap")
    private int presion_abdominal;
    
    @NotNull(message = "Debe ingresar un valor.")
    @Column(name="presion_arterial_clap")
    private float presion_arterial;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="aspecto_general_clap")
    private boolean aspecto_general;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="agudeza_visual_clap")
    private boolean agudeza_visual;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="salud_bucal_clap")
    private boolean salud_bucal;
     
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="cardio_pulmonar_clap")
    private boolean cardio_pulmonar;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="abdomen_clap")
    private boolean abdomen;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="columna_clap")
    private boolean columna;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="extremidades_clap")
    private boolean extremidades;
    
    @Column(name="otra_area_examen_fisico_clap")
    private String otra_area_examen_fisico;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="tanner_con_foto_clap")
    private boolean tanner_con_foto;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="tanner_mama_clap")
    private boolean tanner_mama;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="tanner_genital_clap")
    private boolean tanner_genital;
    
    @Column(name="observaciones_examen_fisico_clap",columnDefinition = "TEXT")
    private String observaciones_examen_fisico;
    
    //final
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="impresion_diagnostica_clap",columnDefinition = "TEXT")
    private String impresion_diagnostica;
    
    @NotNull(message = "Debe seleccionar una opción.")
    @Column(name="indicaciones_interconsultas_clap",columnDefinition = "TEXT")
    private String indicaciones_interconsultas;
    
    @ManyToOne
    @JoinColumn(name="paciente_clap")
    private paciente paciente;
    
    @ManyToOne
    @JoinColumn(name="funcionario_PSA_clap")
    private Usuario funcionario;
    
    @NotNull
    @Column(name="riesgo_cardiovascular_clap")
    private boolean riesgo_cardiovascular;
    
    @NotNull
    @Column(name="riesgo_ssr_clap")
    private String riesgo_ssr;
    
    @NotNull
    @Column(name="riesgo_salud_mental_clap")
    private String riesgo_salud_mental;
    
    @NotNull
    @Column(name="riesgo_oh_drogas_clap")
    private String riesgo_oh_drogas;
    
    @NotNull
    @Column(name="riesgo_nutricional_clap")
    private String riesgo_nutricional;
    
    @NotNull
    @Column(name="riesgo_social_clap")
    private String riesgo_social;

    public Long getNumero_consulta() {
        return numero_consulta;
    }

    public void setNumero_consulta(Long numero_consulta) {
        this.numero_consulta = numero_consulta;
    }

    public Date getFecha_consulta() {
        return fecha_consulta;
    }

    public void setFecha_consulta(Date fecha_consulta) {
        this.fecha_consulta = fecha_consulta;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAcompañante() {
        return acompañante;
    }

    public void setAcompañante(String acompañante) {
        this.acompañante = acompañante;
    }

    public String getMotivo_consulta_adolescente_1() {
        return motivo_consulta_adolescente_1;
    }

    public void setMotivo_consulta_adolescente_1(String motivo_consulta_adolescente_1) {
        this.motivo_consulta_adolescente_1 = motivo_consulta_adolescente_1;
    }

    public String getMotivo_consulta_adolescente_2() {
        return motivo_consulta_adolescente_2;
    }

    public void setMotivo_consulta_adolescente_2(String motivo_consulta_adolescente_2) {
        this.motivo_consulta_adolescente_2 = motivo_consulta_adolescente_2;
    }

    public String getMotivo_consulta_adolescente_3() {
        return motivo_consulta_adolescente_3;
    }

    public void setMotivo_consulta_adolescente_3(String motivo_consulta_adolescente_3) {
        this.motivo_consulta_adolescente_3 = motivo_consulta_adolescente_3;
    }

    public String getMotivo_consulta_acompañante_1() {
        return motivo_consulta_acompañante_1;
    }

    public void setMotivo_consulta_acompañante_1(String motivo_consulta_acompañante_1) {
        this.motivo_consulta_acompañante_1 = motivo_consulta_acompañante_1;
    }

    public String getMotivo_consulta_acompañante_2() {
        return motivo_consulta_acompañante_2;
    }

    public void setMotivo_consulta_acompañante_2(String motivo_consulta_acompañante_2) {
        this.motivo_consulta_acompañante_2 = motivo_consulta_acompañante_2;
    }

    public String getMotivo_consulta_acompañante_3() {
        return motivo_consulta_acompañante_3;
    }

    public void setMotivo_consulta_acompañante_3(String motivo_consulta_acompañante_3) {
        this.motivo_consulta_acompañante_3 = motivo_consulta_acompañante_3;
    }

    public String getDescripcion_motivo_consulta() {
        return descripcion_motivo_consulta;
    }

    public void setDescripcion_motivo_consulta(String descripcion_motivo_consulta) {
        this.descripcion_motivo_consulta = descripcion_motivo_consulta;
    }

    public int getPerinatales_normales() {
        return perinatales_normales;
    }

    public void setPerinatales_normales(int perinatales_normales) {
        this.perinatales_normales = perinatales_normales;
    }

    public int getAlergias_normales() {
        return alergias_normales;
    }

    public void setAlergias_normales(int alergias_normales) {
        this.alergias_normales = alergias_normales;
    }

    public int getVacunas_completas() {
        return vacunas_completas;
    }

    public void setVacunas_completas(int vacunas_completas) {
        this.vacunas_completas = vacunas_completas;
    }

    public int getEnfermedades_importantes() {
        return enfermedades_importantes;
    }

    public void setEnfermedades_importantes(int enfermedades_importantes) {
        this.enfermedades_importantes = enfermedades_importantes;
    }

    public int getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(int discapacidad) {
        this.discapacidad = discapacidad;
    }

    public int getAccidentes_relevantes() {
        return accidentes_relevantes;
    }

    public void setAccidentes_relevantes(int accidentes_relevantes) {
        this.accidentes_relevantes = accidentes_relevantes;
    }

    public int getCirugia_hospitalizaciones() {
        return cirugia_hospitalizaciones;
    }

    public void setCirugia_hospitalizaciones(int cirugia_hospitalizaciones) {
        this.cirugia_hospitalizaciones = cirugia_hospitalizaciones;
    }

    public int getUso_medicamentos() {
        return uso_medicamentos;
    }

    public void setUso_medicamentos(int uso_medicamentos) {
        this.uso_medicamentos = uso_medicamentos;
    }

    public int getProblemas_salud_mental() {
        return problemas_salud_mental;
    }

    public void setProblemas_salud_mental(int problemas_salud_mental) {
        this.problemas_salud_mental = problemas_salud_mental;
    }

    public int getViolencia() {
        return violencia;
    }

    public void setViolencia(int violencia) {
        this.violencia = violencia;
    }

    public int getAntecedentes_judiciales() {
        return antecedentes_judiciales;
    }

    public void setAntecedentes_judiciales(int antecedentes_judiciales) {
        this.antecedentes_judiciales = antecedentes_judiciales;
    }

    public int getOtros() {
        return otros;
    }

    public void setOtros(int otros) {
        this.otros = otros;
    }

    public String getObservaciones_antecdentes_personales() {
        return observaciones_antecdentes_personales;
    }

    public void setObservaciones_antecdentes_personales(String observaciones_antecdentes_personales) {
        this.observaciones_antecdentes_personales = observaciones_antecdentes_personales;
    }

    public int getEnfermedades_importantes_familia() {
        return enfermedades_importantes_familia;
    }

    public void setEnfermedades_importantes_familia(int enfermedades_importantes_familia) {
        this.enfermedades_importantes_familia = enfermedades_importantes_familia;
    }

    public int getObesidad_familia() {
        return obesidad_familia;
    }

    public void setObesidad_familia(int obesidad_familia) {
        this.obesidad_familia = obesidad_familia;
    }

    public int getProblemas_salud_mental_familia() {
        return problemas_salud_mental_familia;
    }

    public void setProblemas_salud_mental_familia(int problemas_salud_mental_familia) {
        this.problemas_salud_mental_familia = problemas_salud_mental_familia;
    }

    public int getViolencia_intrafamiliar() {
        return violencia_intrafamiliar;
    }

    public void setViolencia_intrafamiliar(int violencia_intrafamiliar) {
        this.violencia_intrafamiliar = violencia_intrafamiliar;
    }

    public int getAlcohol_y_otras_drogas() {
        return alcohol_y_otras_drogas;
    }

    public void setAlcohol_y_otras_drogas(int alcohol_y_otras_drogas) {
        this.alcohol_y_otras_drogas = alcohol_y_otras_drogas;
    }

    public int getPadre_adolescente() {
        return padre_adolescente;
    }

    public void setPadre_adolescente(int padre_adolescente) {
        this.padre_adolescente = padre_adolescente;
    }

    public int getJudiciales() {
        return judiciales;
    }

    public void setJudiciales(int judiciales) {
        this.judiciales = judiciales;
    }

    public int getOtros_antecedentes_familiares() {
        return otros_antecedentes_familiares;
    }

    public void setOtros_antecedentes_familiares(int otros_antecedentes_familiares) {
        this.otros_antecedentes_familiares = otros_antecedentes_familiares;
    }

    public String getObservaciones_antecedentes_familiares() {
        return observaciones_antecedentes_familiares;
    }

    public void setObservaciones_antecedentes_familiares(String observaciones_antecedentes_familiares) {
        this.observaciones_antecedentes_familiares = observaciones_antecedentes_familiares;
    }

    public boolean isVive_con_solo() {
        return vive_con_solo;
    }

    public void setVive_con_solo(boolean vive_con_solo) {
        this.vive_con_solo = vive_con_solo;
    }

    public boolean isVive_con_madre() {
        return vive_con_madre;
    }

    public void setVive_con_madre(boolean vive_con_madre) {
        this.vive_con_madre = vive_con_madre;
    }

    public boolean isVive_con_padre() {
        return vive_con_padre;
    }

    public void setVive_con_padre(boolean vive_con_padre) {
        this.vive_con_padre = vive_con_padre;
    }

    public boolean isVive_en_institucion() {
        return vive_en_institucion;
    }

    public void setVive_en_institucion(boolean vive_en_institucion) {
        this.vive_en_institucion = vive_en_institucion;
    }

    public boolean isVive_con_otros() {
        return vive_con_otros;
    }

    public void setVive_con_otros(boolean vive_con_otros) {
        this.vive_con_otros = vive_con_otros;
    }

    public String getVive_con_especificación() {
        return vive_con_especificación;
    }

    public void setVive_con_especificación(String vive_con_especificación) {
        this.vive_con_especificación = vive_con_especificación;
    }

    public boolean isComparte_cama() {
        return comparte_cama;
    }

    public void setComparte_cama(boolean comparte_cama) {
        this.comparte_cama = comparte_cama;
    }

    public String getEspecificacion_comparte_cama() {
        return especificacion_comparte_cama;
    }

    public void setEspecificacion_comparte_cama(String especificacion_comparte_cama) {
        this.especificacion_comparte_cama = especificacion_comparte_cama;
    }

    public int getNivel_instruccion_madre() {
        return nivel_instruccion_madre;
    }

    public void setNivel_instruccion_madre(int nivel_instruccion_madre) {
        this.nivel_instruccion_madre = nivel_instruccion_madre;
    }

    public int getNivel_instruccion_padre() {
        return nivel_instruccion_padre;
    }

    public void setNivel_instruccion_padre(int nivel_instruccion_padre) {
        this.nivel_instruccion_padre = nivel_instruccion_padre;
    }

    public int getNivel_instruccion_pareja() {
        return nivel_instruccion_pareja;
    }

    public void setNivel_instruccion_pareja(int nivel_instruccion_pareja) {
        this.nivel_instruccion_pareja = nivel_instruccion_pareja;
    }

    public String getOcupacion_madre() {
        return ocupacion_madre;
    }

    public void setOcupacion_madre(String ocupacion_madre) {
        this.ocupacion_madre = ocupacion_madre;
    }

    public String getOcupacion_padre() {
        return ocupacion_padre;
    }

    public void setOcupacion_padre(String ocupacion_padre) {
        this.ocupacion_padre = ocupacion_padre;
    }

    public String getOcupacion_pareja() {
        return ocupacion_pareja;
    }

    public void setOcupacion_pareja(String ocupacion_pareja) {
        this.ocupacion_pareja = ocupacion_pareja;
    }

    public int getPercepcion_familia() {
        return percepcion_familia;
    }

    public void setPercepcion_familia(int percepcion_familia) {
        this.percepcion_familia = percepcion_familia;
    }

    public String getDiagrama_familiar() {
        return diagrama_familiar;
    }

    public void setDiagrama_familiar(String diagrama_familiar) {
        this.diagrama_familiar = diagrama_familiar;
    }

    public String getObservaciones_familia() {
        return observaciones_familia;
    }

    public void setObservaciones_familia(String observaciones_familia) {
        this.observaciones_familia = observaciones_familia;
    }

    public boolean isCondiciones_sanitarias() {
        return condiciones_sanitarias;
    }

    public void setCondiciones_sanitarias(boolean condiciones_sanitarias) {
        this.condiciones_sanitarias = condiciones_sanitarias;
    }

    public boolean isHacinamiento() {
        return hacinamiento;
    }

    public void setHacinamiento(boolean hacinamiento) {
        this.hacinamiento = hacinamiento;
    }

    public String getObservaciones_vivienda() {
        return observaciones_vivienda;
    }

    public void setObservaciones_vivienda(String observaciones_vivienda) {
        this.observaciones_vivienda = observaciones_vivienda;
    }

    public boolean isEstudia() {
        return estudia;
    }

    public void setEstudia(boolean estudia) {
        this.estudia = estudia;
    }

    public int getNivel_educacion() {
        return nivel_educacion;
    }

    public void setNivel_educacion(int nivel_educacion) {
        this.nivel_educacion = nivel_educacion;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getAños_repetidos() {
        return años_repetidos;
    }

    public void setAños_repetidos(int años_repetidos) {
        this.años_repetidos = años_repetidos;
    }

    public String getCausa_años_repetidos() {
        return causa_años_repetidos;
    }

    public void setCausa_años_repetidos(String causa_años_repetidos) {
        this.causa_años_repetidos = causa_años_repetidos;
    }

    public boolean isProblemas_escuela() {
        return problemas_escuela;
    }

    public void setProblemas_escuela(boolean problemas_escuela) {
        this.problemas_escuela = problemas_escuela;
    }

    public boolean isViolencia_escolar() {
        return violencia_escolar;
    }

    public void setViolencia_escolar(boolean violencia_escolar) {
        this.violencia_escolar = violencia_escolar;
    }

    public boolean isDesercion_exclusion() {
        return desercion_exclusion;
    }

    public void setDesercion_exclusion(boolean desercion_exclusion) {
        this.desercion_exclusion = desercion_exclusion;
    }

    public String getCausa_desercion_exclusion() {
        return causa_desercion_exclusion;
    }

    public void setCausa_desercion_exclusion(String causa_desercion_exclusion) {
        this.causa_desercion_exclusion = causa_desercion_exclusion;
    }

    public int getPercepcion_rendimiento() {
        return percepcion_rendimiento;
    }

    public void setPercepcion_rendimiento(int percepcion_rendimiento) {
        this.percepcion_rendimiento = percepcion_rendimiento;
    }

    public String getObservaciones_educacion() {
        return observaciones_educacion;
    }

    public void setObservaciones_educacion(String observaciones_educacion) {
        this.observaciones_educacion = observaciones_educacion;
    }

    public boolean isTrabaja() {
        return trabaja;
    }

    public void setTrabaja(boolean trabaja) {
        this.trabaja = trabaja;
    }

    public int getHoras_trabajo() {
        return horas_trabajo;
    }

    public void setHoras_trabajo(int horas_trabajo) {
        this.horas_trabajo = horas_trabajo;
    }

    public boolean isTrabajo_infantil() {
        return trabajo_infantil;
    }

    public void setTrabajo_infantil(boolean trabajo_infantil) {
        this.trabajo_infantil = trabajo_infantil;
    }

    public boolean isTrabajo_juvenil() {
        return trabajo_juvenil;
    }

    public void setTrabajo_juvenil(boolean trabajo_juvenil) {
        this.trabajo_juvenil = trabajo_juvenil;
    }

    public boolean isPeores_formas() {
        return peores_formas;
    }

    public void setPeores_formas(boolean peores_formas) {
        this.peores_formas = peores_formas;
    }

    public boolean isSer_dom_no_remu_peligroso() {
        return ser_dom_no_remu_peligroso;
    }

    public void setSer_dom_no_remu_peligroso(boolean ser_dom_no_remu_peligroso) {
        this.ser_dom_no_remu_peligroso = ser_dom_no_remu_peligroso;
    }

    public int getRazon_de_trabajo() {
        return razon_de_trabajo;
    }

    public void setRazon_de_trabajo(int razon_de_trabajo) {
        this.razon_de_trabajo = razon_de_trabajo;
    }

    public int getLegalizado() {
        return legalizado;
    }

    public void setLegalizado(int legalizado) {
        this.legalizado = legalizado;
    }

    public String getTipo_de_trabajo() {
        return tipo_de_trabajo;
    }

    public void setTipo_de_trabajo(String tipo_de_trabajo) {
        this.tipo_de_trabajo = tipo_de_trabajo;
    }

    public String getObservaciones_trabajo() {
        return observaciones_trabajo;
    }

    public void setObservaciones_trabajo(String observaciones_trabajo) {
        this.observaciones_trabajo = observaciones_trabajo;
    }

    public int getAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(int aceptacion) {
        this.aceptacion = aceptacion;
    }

    public boolean isPareja() {
        return pareja;
    }

    public void setPareja(boolean pareja) {
        this.pareja = pareja;
    }

    public int getEdad_pareja() {
        return edad_pareja;
    }

    public void setEdad_pareja(int edad_pareja) {
        this.edad_pareja = edad_pareja;
    }

    public boolean isViolencia_pareja() {
        return violencia_pareja;
    }

    public void setViolencia_pareja(boolean violencia_pareja) {
        this.violencia_pareja = violencia_pareja;
    }

    public boolean isAmigos() {
        return amigos;
    }

    public void setAmigos(boolean amigos) {
        this.amigos = amigos;
    }

    public boolean isSuicidalidad_amigos() {
        return suicidalidad_amigos;
    }

    public void setSuicidalidad_amigos(boolean suicidalidad_amigos) {
        this.suicidalidad_amigos = suicidalidad_amigos;
    }

    public int getHoras_actividad_fisica() {
        return horas_actividad_fisica;
    }

    public void setHoras_actividad_fisica(int horas_actividad_fisica) {
        this.horas_actividad_fisica = horas_actividad_fisica;
    }

    public int getHoras_tv() {
        return horas_tv;
    }

    public void setHoras_tv(int horas_tv) {
        this.horas_tv = horas_tv;
    }

    public int getHoras_computador_consola() {
        return horas_computador_consola;
    }

    public void setHoras_computador_consola(int horas_computador_consola) {
        this.horas_computador_consola = horas_computador_consola;
    }

    public boolean isCyberbulling() {
        return cyberbulling;
    }

    public void setCyberbulling(boolean cyberbulling) {
        this.cyberbulling = cyberbulling;
    }

    public boolean isGrooming() {
        return grooming;
    }

    public void setGrooming(boolean grooming) {
        this.grooming = grooming;
    }

    public boolean isOtras_actividades() {
        return otras_actividades;
    }

    public void setOtras_actividades(boolean otras_actividades) {
        this.otras_actividades = otras_actividades;
    }

    public String getEspecificacion_otras_actividades() {
        return especificacion_otras_actividades;
    }

    public void setEspecificacion_otras_actividades(String especificacion_otras_actividades) {
        this.especificacion_otras_actividades = especificacion_otras_actividades;
    }

    public String getObservaciones_vida_social() {
        return observaciones_vida_social;
    }

    public void setObservaciones_vida_social(String observaciones_vida_social) {
        this.observaciones_vida_social = observaciones_vida_social;
    }

    public boolean isSueño_normal() {
        return sueño_normal;
    }

    public void setSueño_normal(boolean sueño_normal) {
        this.sueño_normal = sueño_normal;
    }

    public int getHoras_sueño() {
        return horas_sueño;
    }

    public void setHoras_sueño(int horas_sueño) {
        this.horas_sueño = horas_sueño;
    }

    public boolean isAlimentacion_adecuada() {
        return alimentacion_adecuada;
    }

    public void setAlimentacion_adecuada(boolean alimentacion_adecuada) {
        this.alimentacion_adecuada = alimentacion_adecuada;
    }

    public int getComidas_familia() {
        return comidas_familia;
    }

    public void setComidas_familia(int comidas_familia) {
        this.comidas_familia = comidas_familia;
    }

    public boolean isAlimentacion_especial() {
        return alimentacion_especial;
    }

    public void setAlimentacion_especial(boolean alimentacion_especial) {
        this.alimentacion_especial = alimentacion_especial;
    }

    public String getEspecificacion_alimentacion_especial() {
        return especificacion_alimentacion_especial;
    }

    public void setEspecificacion_alimentacion_especial(String especificacion_alimentacion_especial) {
        this.especificacion_alimentacion_especial = especificacion_alimentacion_especial;
    }

    public boolean isTabaco() {
        return tabaco;
    }

    public void setTabaco(boolean tabaco) {
        this.tabaco = tabaco;
    }

    public int getCigarros_dia() {
        return cigarros_dia;
    }

    public void setCigarros_dia(int cigarros_dia) {
        this.cigarros_dia = cigarros_dia;
    }

    public boolean isConsumo_alcohol() {
        return consumo_alcohol;
    }

    public void setConsumo_alcohol(boolean consumo_alcohol) {
        this.consumo_alcohol = consumo_alcohol;
    }

    public boolean isConsumo_marihuana() {
        return consumo_marihuana;
    }

    public void setConsumo_marihuana(boolean consumo_marihuana) {
        this.consumo_marihuana = consumo_marihuana;
    }

    public boolean isConsumo_otra_sustancia() {
        return consumo_otra_sustancia;
    }

    public void setConsumo_otra_sustancia(boolean consumo_otra_sustancia) {
        this.consumo_otra_sustancia = consumo_otra_sustancia;
    }

    public boolean isEspecificacion_consumo_otra_sustancia() {
        return especificacion_consumo_otra_sustancia;
    }

    public void setEspecificacion_consumo_otra_sustancia(boolean especificacion_consumo_otra_sustancia) {
        this.especificacion_consumo_otra_sustancia = especificacion_consumo_otra_sustancia;
    }

    public boolean isSeguridad_vial() {
        return seguridad_vial;
    }

    public void setSeguridad_vial(boolean seguridad_vial) {
        this.seguridad_vial = seguridad_vial;
    }

    public String getObservaciones_habitos_consumo() {
        return observaciones_habitos_consumo;
    }

    public void setObservaciones_habitos_consumo(String observaciones_habitos_consumo) {
        this.observaciones_habitos_consumo = observaciones_habitos_consumo;
    }

    public String getEdad_menarca_espermarca() {
        return edad_menarca_espermarca;
    }

    public void setEdad_menarca_espermarca(String edad_menarca_espermarca) {
        this.edad_menarca_espermarca = edad_menarca_espermarca;
    }

    public Date getFecha_ultima_menstruacion() {
        return fecha_ultima_menstruacion;
    }

    public void setFecha_ultima_menstruacion(Date fecha_ultima_menstruacion) {
        this.fecha_ultima_menstruacion = fecha_ultima_menstruacion;
    }

    public boolean isNo_conoce_fecha_ultima_menstruacion() {
        return no_conoce_fecha_ultima_menstruacion;
    }

    public void setNo_conoce_fecha_ultima_menstruacion(boolean no_conoce_fecha_ultima_menstruacion) {
        this.no_conoce_fecha_ultima_menstruacion = no_conoce_fecha_ultima_menstruacion;
    }

    public int getCiclos_regulares() {
        return ciclos_regulares;
    }

    public void setCiclos_regulares(int ciclos_regulares) {
        this.ciclos_regulares = ciclos_regulares;
    }

    public int getDismenorrea() {
        return dismenorrea;
    }

    public void setDismenorrea(int dismenorrea) {
        this.dismenorrea = dismenorrea;
    }

    public boolean isFlujo_secrecion_patologico() {
        return flujo_secrecion_patologico;
    }

    public void setFlujo_secrecion_patologico(boolean flujo_secrecion_patologico) {
        this.flujo_secrecion_patologico = flujo_secrecion_patologico;
    }

    public boolean isIts_vih() {
        return its_vih;
    }

    public void setIts_vih(boolean its_vih) {
        this.its_vih = its_vih;
    }

    public String getEspecificacion_its_vih() {
        return especificacion_its_vih;
    }

    public void setEspecificacion_its_vih(String especificacion_its_vih) {
        this.especificacion_its_vih = especificacion_its_vih;
    }

    public int getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(int tratamiento) {
        this.tratamiento = tratamiento;
    }

    public int getTratamiento_contactos() {
        return tratamiento_contactos;
    }

    public void setTratamiento_contactos(int tratamiento_contactos) {
        this.tratamiento_contactos = tratamiento_contactos;
    }

    public int getEmbarazos() {
        return embarazos;
    }

    public void setEmbarazos(int embarazos) {
        this.embarazos = embarazos;
    }

    public int getHijos() {
        return hijos;
    }

    public void setHijos(int hijos) {
        this.hijos = hijos;
    }

    public int getAbortos() {
        return abortos;
    }

    public void setAbortos(int abortos) {
        this.abortos = abortos;
    }

    public String getObservaciones_gineco_urologico() {
        return observaciones_gineco_urologico;
    }

    public void setObservaciones_gineco_urologico(String observaciones_gineco_urologico) {
        this.observaciones_gineco_urologico = observaciones_gineco_urologico;
    }

    public int getOrientacion_sexual() {
        return orientacion_sexual;
    }

    public void setOrientacion_sexual(int orientacion_sexual) {
        this.orientacion_sexual = orientacion_sexual;
    }

    public String getEspecificacion_orientacion_sexual() {
        return especificacion_orientacion_sexual;
    }

    public void setEspecificacion_orientacion_sexual(String especificacion_orientacion_sexual) {
        this.especificacion_orientacion_sexual = especificacion_orientacion_sexual;
    }

    public int getConducta_sexual() {
        return conducta_sexual;
    }

    public void setConducta_sexual(int conducta_sexual) {
        this.conducta_sexual = conducta_sexual;
    }

    public String getEdad_inicio_conducta_sexual() {
        return edad_inicio_conducta_sexual;
    }

    public void setEdad_inicio_conducta_sexual(String edad_inicio_conducta_sexual) {
        this.edad_inicio_conducta_sexual = edad_inicio_conducta_sexual;
    }

    public int getRelaciones_sexuales() {
        return relaciones_sexuales;
    }

    public void setRelaciones_sexuales(int relaciones_sexuales) {
        this.relaciones_sexuales = relaciones_sexuales;
    }

    public int getPareja_sexual() {
        return pareja_sexual;
    }

    public void setPareja_sexual(int pareja_sexual) {
        this.pareja_sexual = pareja_sexual;
    }

    public int getDificultades_sexuales() {
        return dificultades_sexuales;
    }

    public void setDificultades_sexuales(int dificultades_sexuales) {
        this.dificultades_sexuales = dificultades_sexuales;
    }

    public int getAnticoncepcion() {
        return anticoncepcion;
    }

    public void setAnticoncepcion(int anticoncepcion) {
        this.anticoncepcion = anticoncepcion;
    }

    public int getUso_mac() {
        return uso_mac;
    }

    public void setUso_mac(int uso_mac) {
        this.uso_mac = uso_mac;
    }

    public String getEspecificacion_uso_mac() {
        return especificacion_uso_mac;
    }

    public void setEspecificacion_uso_mac(String especificacion_uso_mac) {
        this.especificacion_uso_mac = especificacion_uso_mac;
    }

    public String getRazon_no_uso_mac() {
        return razon_no_uso_mac;
    }

    public void setRazon_no_uso_mac(String razon_no_uso_mac) {
        this.razon_no_uso_mac = razon_no_uso_mac;
    }

    public boolean isConsejeria_uso_mac() {
        return consejeria_uso_mac;
    }

    public void setConsejeria_uso_mac(boolean consejeria_uso_mac) {
        this.consejeria_uso_mac = consejeria_uso_mac;
    }

    public boolean isAco_emergencia() {
        return aco_emergencia;
    }

    public void setAco_emergencia(boolean aco_emergencia) {
        this.aco_emergencia = aco_emergencia;
    }

    public boolean isAbuso_sexual() {
        return abuso_sexual;
    }

    public void setAbuso_sexual(boolean abuso_sexual) {
        this.abuso_sexual = abuso_sexual;
    }

    public boolean isReparacion_abuso_sexual() {
        return reparacion_abuso_sexual;
    }

    public void setReparacion_abuso_sexual(boolean reparacion_abuso_sexual) {
        this.reparacion_abuso_sexual = reparacion_abuso_sexual;
    }

    public String getObservaciones_sexualidad() {
        return observaciones_sexualidad;
    }

    public void setObservaciones_sexualidad(String observaciones_sexualidad) {
        this.observaciones_sexualidad = observaciones_sexualidad;
    }

    public int getImagen_corporal_mac() {
        return imagen_corporal_mac;
    }

    public void setImagen_corporal_mac(int imagen_corporal_mac) {
        this.imagen_corporal_mac = imagen_corporal_mac;
    }

    public int getBienestas_emocional_mac() {
        return bienestas_emocional_mac;
    }

    public void setBienestas_emocional_mac(int bienestas_emocional_mac) {
        this.bienestas_emocional_mac = bienestas_emocional_mac;
    }

    public int getVida_proyecto_mac() {
        return vida_proyecto_mac;
    }

    public void setVida_proyecto_mac(int vida_proyecto_mac) {
        this.vida_proyecto_mac = vida_proyecto_mac;
    }

    public boolean isIdeacion_suicida_mac() {
        return ideacion_suicida_mac;
    }

    public void setIdeacion_suicida_mac(boolean ideacion_suicida_mac) {
        this.ideacion_suicida_mac = ideacion_suicida_mac;
    }

    public boolean isIntento_suicida_mac() {
        return intento_suicida_mac;
    }

    public void setIntento_suicida_mac(boolean intento_suicida_mac) {
        this.intento_suicida_mac = intento_suicida_mac;
    }

    public int getReferente_adulto_mac() {
        return referente_adulto_mac;
    }

    public void setReferente_adulto_mac(int referente_adulto_mac) {
        this.referente_adulto_mac = referente_adulto_mac;
    }

    public String getNombre_referente_adulto_mac() {
        return nombre_referente_adulto_mac;
    }

    public void setNombre_referente_adulto_mac(String nombre_referente_adulto_mac) {
        this.nombre_referente_adulto_mac = nombre_referente_adulto_mac;
    }

    public String getTelefono_referente_adulto_mac() {
        return telefono_referente_adulto_mac;
    }

    public void setTelefono_referente_adulto_mac(String telefono_referente_adulto_mac) {
        this.telefono_referente_adulto_mac = telefono_referente_adulto_mac;
    }

    public String getObservaciones_psico_emocional() {
        return observaciones_psico_emocional;
    }

    public void setObservaciones_psico_emocional(String observaciones_psico_emocional) {
        this.observaciones_psico_emocional = observaciones_psico_emocional;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getDe_peso() {
        return de_peso;
    }

    public void setDe_peso(int de_peso) {
        this.de_peso = de_peso;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public int getDe_talla() {
        return de_talla;
    }

    public void setDe_talla(int de_talla) {
        this.de_talla = de_talla;
    }

    public int getPresion_abdominal() {
        return presion_abdominal;
    }

    public void setPresion_abdominal(int presion_abdominal) {
        this.presion_abdominal = presion_abdominal;
    }

    public float getPresion_arterial() {
        return presion_arterial;
    }

    public void setPresion_arterial(float presion_arterial) {
        this.presion_arterial = presion_arterial;
    }

    public boolean isAspecto_general() {
        return aspecto_general;
    }

    public void setAspecto_general(boolean aspecto_general) {
        this.aspecto_general = aspecto_general;
    }

    public boolean isAgudeza_visual() {
        return agudeza_visual;
    }

    public void setAgudeza_visual(boolean agudeza_visual) {
        this.agudeza_visual = agudeza_visual;
    }

    public boolean isSalud_bucal() {
        return salud_bucal;
    }

    public void setSalud_bucal(boolean salud_bucal) {
        this.salud_bucal = salud_bucal;
    }

    public boolean isCardio_pulmonar() {
        return cardio_pulmonar;
    }

    public void setCardio_pulmonar(boolean cardio_pulmonar) {
        this.cardio_pulmonar = cardio_pulmonar;
    }

    public boolean isAbdomen() {
        return abdomen;
    }

    public void setAbdomen(boolean abdomen) {
        this.abdomen = abdomen;
    }

    public boolean isColumna() {
        return columna;
    }

    public void setColumna(boolean columna) {
        this.columna = columna;
    }

    public boolean isExtremidades() {
        return extremidades;
    }

    public void setExtremidades(boolean extremidades) {
        this.extremidades = extremidades;
    }

    public String getOtra_area_examen_fisico() {
        return otra_area_examen_fisico;
    }

    public void setOtra_area_examen_fisico(String otra_area_examen_fisico) {
        this.otra_area_examen_fisico = otra_area_examen_fisico;
    }

    public boolean isTanner_con_foto() {
        return tanner_con_foto;
    }

    public void setTanner_con_foto(boolean tanner_con_foto) {
        this.tanner_con_foto = tanner_con_foto;
    }

    public boolean isTanner_mama() {
        return tanner_mama;
    }

    public void setTanner_mama(boolean tanner_mama) {
        this.tanner_mama = tanner_mama;
    }

    public boolean isTanner_genital() {
        return tanner_genital;
    }

    public void setTanner_genital(boolean tanner_genital) {
        this.tanner_genital = tanner_genital;
    }

    public String getObservaciones_examen_fisico() {
        return observaciones_examen_fisico;
    }

    public void setObservaciones_examen_fisico(String observaciones_examen_fisico) {
        this.observaciones_examen_fisico = observaciones_examen_fisico;
    }

    public String getImpresion_diagnostica() {
        return impresion_diagnostica;
    }

    public void setImpresion_diagnostica(String impresion_diagnostica) {
        this.impresion_diagnostica = impresion_diagnostica;
    }

    public String getIndicaciones_interconsultas() {
        return indicaciones_interconsultas;
    }

    public void setIndicaciones_interconsultas(String indicaciones_interconsultas) {
        this.indicaciones_interconsultas = indicaciones_interconsultas;
    }

    public paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(paciente paciente) {
        this.paciente = paciente;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario) {
        this.funcionario = funcionario;
    }

    public boolean isRiesgo_cardiovascular() {
        return riesgo_cardiovascular;
    }

    public void setRiesgo_cardiovascular(boolean riesgo_cardiovascular) {
        this.riesgo_cardiovascular = riesgo_cardiovascular;
    }

    public String getRiesgo_ssr() {
        return riesgo_ssr;
    }

    public void setRiesgo_ssr(String riesgo_ssr) {
        this.riesgo_ssr = riesgo_ssr;
    }

    public String getRiesgo_salud_mental() {
        return riesgo_salud_mental;
    }

    public void setRiesgo_salud_mental(String riesgo_salud_mental) {
        this.riesgo_salud_mental = riesgo_salud_mental;
    }

    public String getRiesgo_oh_drogas() {
        return riesgo_oh_drogas;
    }

    public void setRiesgo_oh_drogas(String riesgo_oh_drogas) {
        this.riesgo_oh_drogas = riesgo_oh_drogas;
    }

    public String getRiesgo_nutricional() {
        return riesgo_nutricional;
    }

    public void setRiesgo_nutricional(String riesgo_nutricional) {
        this.riesgo_nutricional = riesgo_nutricional;
    }

    public String getRiesgo_social() {
        return riesgo_social;
    }

    public void setRiesgo_social(String riesgo_social) {
        this.riesgo_social = riesgo_social;
    }
    
    
    
    public String getNombre_social() {
        return nombre_social;
    }

    public void setNombre_social(String nombre_social) {
        this.nombre_social = nombre_social;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getRUN() {
        return RUN;
    }

    public void setRUN(int RUN) {
        this.RUN = RUN;
    }

    public String getDV() {
        return DV;
    }

    public void setDV(String DV) {
        this.DV = DV;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento =fecha_nacimiento;
    }

    public region getRegion_residencia() {
        return region_residencia;
    }

    public void setRegion_residencia(region region_residencia) {
        this.region_residencia = region_residencia;
    }

    public comuna getComuna_residencia() {
        return comuna_residencia;
    }

    public void setComuna_residencia(comuna comuna_residencia) {
        this.comuna_residencia = comuna_residencia;
    }

    public String getCalle_direccion() {
        return calle_direccion;
    }

    public void setCalle_direccion(String calle_direccion) {
        this.calle_direccion = calle_direccion;
    }

    public String getNumero_direccion() {
        return numero_direccion;
    }

    public void setNumero_direccion(String numero_direccion) {
        this.numero_direccion = numero_direccion;
    }

    public String getResto_direccion() {
        return resto_direccion;
    }

    public void setResto_direccion(String resto_direccion) {
        this.resto_direccion = resto_direccion;
    }

    public String getTelefono_fijo() {
        return telefono_fijo;
    }

    public void setTelefono_fijo(String telefono_fijo) {
        this.telefono_fijo = telefono_fijo;
    }

    public String getTeléfono_movil() {
        return teléfono_movil;
    }

    public void setTeléfono_movil(String teléfono_movil) {
        this.teléfono_movil = teléfono_movil;
    }

    public ley_social getPrograma_social() {
        return programa_social;
    }

    public void setPrograma_social(ley_social programa_social) {
        this.programa_social = programa_social;
    }

    public prevision getPrevision() {
        return prevision;
    }

    public void setPrevision(prevision prevision) {
        this.prevision = prevision;
    }

    public estado_civil getEstado_conyugal() {
        return estado_conyugal;
    }

    public void setEstado_conyugal(estado_civil estado_conyugal) {
        this.estado_conyugal = estado_conyugal;
    }

    public int getGrupo_fonasa() {
        return grupo_fonasa;
    }

    public void setGrupo_fonasa(int grupo_fonasa) {
        this.grupo_fonasa = grupo_fonasa;
    }

    public pueblo_originario getPueblo_originario() {
        return pueblo_originario;
    }

    public void setPueblo_originario(pueblo_originario pueblo_originario) {
        this.pueblo_originario = pueblo_originario;
    }

    public cesfam getCesfam() {
        return cesfam;
    }

    public void setCesfam(cesfam cesfam) {
        this.cesfam = cesfam;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof clap)) {
            return false;
        }
        clap other = (clap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.clap[ id=" + id + " ]";
    }
    
}