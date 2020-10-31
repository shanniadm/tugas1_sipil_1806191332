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
    @Column(name = "tanggalLahir", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempatLahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name = "jenisKelamin", nullable = false)
    private Integer jenisKelamin;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idMaskapai", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private MaskapaiModel maskapai;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idAkademi", referencedColumnName = "id", nullable = false)
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
        return this.tanggalLahir;
    }

    public String getTempatLahir(){
        return this.tempatLahir;
    }

    public Integer getJenisKelamin(){
        return this.jenisKelamin;
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
        this.tanggalLahir = tanggal_lahir;
    }

    public void setTempatLahir(String tempat_lahir){
        this.tempatLahir = tempat_lahir;
    }

    public void setJenisKelamin(Integer jenis_kelamin){
        this.jenisKelamin = jenis_kelamin;
    }

    public void setMaskapai(MaskapaiModel maskapai){
        this.maskapai = maskapai;
    }

    public void setAkademi(AkademiModel akademi){
        this.akademi = akademi;
    }
}
