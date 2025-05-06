package cl.api.buho.proyecto.models;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Solicitudes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long id_solicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes clientes;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "observacion_tecnico_soporte", columnDefinition = "TEXT")
    private String observacion_tecnico_soporte;

    @Column(name = "observacion_desarrollador", columnDefinition = "TEXT")
    private String observacion_desarrollador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estados estados;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_revision")
    private EstadosRevisiones estadosRevisiones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tecnico_soporte")
    private TecnicosSoportes tecnicosSoportes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_desarrollador")
    private Desarrolladores desarrolladores;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fecha_creacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fecha_actualizacion;

    @PrePersist
    protected void onCreate() {
        this.fecha_creacion = LocalDateTime.now();
        this.fecha_actualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fecha_actualizacion = LocalDateTime.now();
    }
}
