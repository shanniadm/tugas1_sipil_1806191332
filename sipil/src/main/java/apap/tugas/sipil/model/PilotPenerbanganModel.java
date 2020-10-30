package apap.tugas.sipil.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "pilot_penerbangan")
public class PilotPenerbanganModel implements Serializable{
    @Id
    @Size(max=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_pilot", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PilotModel pilot;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_penerbangan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PenerbanganModel penerbangan;

    @NotNull
    @Column(name = "tanggal_penugasan", nullable = false)
    private Date tanggal_penugasan;

    //Getter 

    public Long getId(){
        return this.id;
    }

    public PilotModel getPilot(){
        return this.pilot;
    }

    public PenerbanganModel getPenerbangan(){
        return this.penerbangan;
    }

    public Date getTanggalPenugasan(){
        return this.tanggal_penugasan;
    }
    
    //Setter

    public void setId(Long id){
        this.id = id;
    }

    public void setPilot(PilotModel pilot){
        this.pilot = pilot;
    }

    public void setPenerbangan(PenerbanganModel penerbangan){
        this.penerbangan = penerbangan;
    }

    public void setTanggalPenugasan(Date tanggal_penugasan){
        this.tanggal_penugasan = tanggal_penugasan;
    }
}
