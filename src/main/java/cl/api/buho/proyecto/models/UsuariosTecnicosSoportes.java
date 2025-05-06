package cl.api.buho.proyecto.models;

import jakarta.persistence.*;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsuariosTecnicosSoportes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_tecnico")
    private Long id_usuario_desarrollador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios usuarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tecnico_soporte", nullable = false)
    private TecnicosSoportes tecnicosSoportes;
}
