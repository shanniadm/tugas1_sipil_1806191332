package apap.tugas.sipil.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity 
@Table(name = "penerbangan")
public class PenerbanganModel implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 16)
    @Column(name = "kode", nullable = false, unique = true)
    private String kode;

    @NotNull
    @Size(max = 255)
    @Column(name = "kotaAsal", nullable = false)
    private String kotaAsal;

    @NotNull
    @Size(max = 255)
    @Column(name = "kotaTujuan", nullable = false)
    private String kotaTujuan;

    @NotNull
    @Column(name = "waktu", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktu;

    //Getter
    public Long getId(){
        return this.id;
    }

    public String getKode(){
        return this.kode;
    }

    public String getKotaAsal(){
        return this.kotaAsal;
    }

    public String getKotaTujuan(){
        return this.kotaTujuan;
    }

    public LocalDateTime getWaktu(){
        return this.waktu;
    }

    //Setter
    public void setId(Long id){
        this.id = id;
    }

    public void setKode(String kode){
        this.kode = kode;
    }

    public void setKotaAsal(String kotaAsal){
        this.kotaAsal = kotaAsal;
    }

    public void setKotaTujuan(String kotaTujuan){
        this.kotaTujuan = kotaTujuan;
    }

    public void setWaktu(LocalDateTime waktu){
        this.waktu = waktu;
    }
}
