package apap.tugas.sipil.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="maskapai")
public class MaskapaiModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull 
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "kode", nullable = false)
    private String kode;

    @OneToMany(mappedBy = "maskapai", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PilotModel> listPilot;

    //Getter
    public Long getId(){
        return this.id;
    }

    public String getNama(){
        return this.nama;
    }

    public String getKode(){
        return this.kode;
    }

    public List<PilotModel> getListPilot(){
        return this.listPilot;
    }

    //Setter

    public void setId(Long id){
        this.id = id;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setLokasi(String kode){
        this.kode = kode;
    }

    public void setListPilot(List<PilotModel> listPilot){
        this.listPilot = listPilot;
    }
}
