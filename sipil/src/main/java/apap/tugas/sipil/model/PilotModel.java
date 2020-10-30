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
@Table(name = "pilot")
public class PilotModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 20)
    private Long id; 

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    
    @Size(max = 13)
    @Column(name = "nip", nullable = false, unique = true)
    private String nip;

    @NotNull
    @Size(max = 255)
    @Column(name = "nik", nullable = false)
    private String nik; 

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    private Date tanggal_lahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_lahir", nullable = false)
    private String tempat_lahir;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private Integer jenis_kelamin;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_maskapai", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MaskapaiModel maskapai;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_akademi", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AkademiModel akademi;

    //Getter 

    public Long getId(){
        return this.id;
    }

    public String getNama(){
        return this.nama;
    }

    public String getNip(){
        return this.nip;
    }

    public String getNik(){
        return this.nik;
    }

    public Date getTanggalLahir(){
        return this.tanggal_lahir;
    }

    public String getTempatLahir(){
        return this.tempat_lahir;
    }

    public Integer getJenisKelamin(){
        return this.jenis_kelamin;
    }

    public MaskapaiModel getMaskapai(){
        return this.maskapai;
    }

    public AkademiModel getAkademi(){
        return this.akademi;
    }

    //Setter

    public void setId(Long id){
        this.id = id;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setNip(String nip){
        this.nip = nip;
    }

    public void setNik(String nik){
        this.nik = nik;
    }

    public void setTanggalLahir(Date tanggal_lahir){
        this.tanggal_lahir = tanggal_lahir;
    }

    public void setTempatLahir(String tempat_lahir){
        this.tempat_lahir = tempat_lahir;
    }

    public void setJenisKelamin(Integer jenis_kelamin){
        this.jenis_kelamin = jenis_kelamin;
    }

    public void setMaskapai(MaskapaiModel maskapai){
        this.maskapai = maskapai;
    }

    public void setAkademi(AkademiModel akademi){
        this.akademi = akademi;
    }
}
