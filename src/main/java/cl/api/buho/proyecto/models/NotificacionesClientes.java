package cl.api.buho.proyecto.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NotificacionesClientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Long id_notificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private Solicitudes solicitudes;

    @Column(name = "observacion", columnDefinition = "TEXT")
    private String observacion;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "leida", nullable = false)
    private boolean leida = false;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
}
