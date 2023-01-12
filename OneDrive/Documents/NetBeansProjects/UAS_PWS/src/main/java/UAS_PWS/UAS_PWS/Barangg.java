/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UAS_PWS.UAS_PWS;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author TUF GAMING
 */
@Entity
@Table(name = "barangg")
@NamedQueries({
    @NamedQuery(name = "Barangg.findAll", query = "SELECT b FROM Barangg b"),
    @NamedQuery(name = "Barangg.findById", query = "SELECT b FROM Barangg b WHERE b.id = :id"),
    @NamedQuery(name = "Barangg.findByNama", query = "SELECT b FROM Barangg b WHERE b.nama = :nama"),
    @NamedQuery(name = "Barangg.findByJumlah", query = "SELECT b FROM Barangg b WHERE b.jumlah = :jumlah")})
public class Barangg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nama")
    private String nama;
    @Column(name = "jumlah")
    private Integer jumlah;
    @Lob
    @Column(name = "gambar")
    private byte[] gambar;

    public Barangg() {
    }

    public Barangg(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
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
        if (!(object instanceof Barangg)) {
            return false;
        }
        Barangg other = (Barangg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UAS_PWS.UAS_PWS.Barangg[ id=" + id + " ]";
    }
    
}
