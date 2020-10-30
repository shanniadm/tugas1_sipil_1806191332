package apap.tugas.sipil.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity 
@Table(name = "penerbangan")
public class PenerbanganModel implements Serializable{
    @Id
    @Size(max = 20)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 16)
    @Column(name = "kode", nullable = false, unique = true)
    private String kode;

    @NotNull
    @Size(max = 255)
    @Column(name = "kota_asal", nullable = false)
    private String kota_asal;

    @NotNull
    @Size(max = 255)
    @Column(name = "kota_tujuan", nullable = false)
    private String kota_tujuan;

    @NotNull
    @Column(name = "waktu", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date waktu;

    //Getter
    public Long getId(){
        return this.id;
    }

    public String getKode(){
        return this.kode;
    }

    public String getKotaAsal(){
        return this.kota_asal;
    }

    public String getKotaTujuan(){
        return this.kota_tujuan;
    }

    public Date getWaktu(){
        return this.waktu;
    }

    //Setter
    public void setId(Long id){
        this.id = id;
    }

    public void setKode(String kode){
        this.kode = kode;
    }

    public void setKotaAsal(String kota_asal){
        this.kota_asal = kota_asal;
    }

    public void setKotaTujuan(String kota_tujuan){
        this.kota_tujuan = kota_tujuan;
    }

    public void setWaktu(Date waktu){
        this.waktu = waktu;
    }
}
