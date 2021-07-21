/*
 * 
 * Jazmín Velázquez Bustos 2021. All Rights Reserved.
 * JACITO 2021. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of JACITO
 * ("Confidential Information"). You shall not disclose such Confidential 
 * Information and shall use it only in accordance with the terms of the license 
 * agreement you entered into with  Jazmín V.B or his authorized licensees. 
 * 
 * JACITO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE 
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR 
 * NON-INFRINGEMENT. JACITO SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY 
 * LICENSEE AS A RESULT OF USING,MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 *
 */
package albo.comics.library.model;

import com.albo.library.json.comics.Item;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jacito
 */
@Entity
@Table(name = "creator")
@NamedQueries({
    @NamedQuery(name = "Creator.findAll", query = "SELECT c FROM Creator c")
    , @NamedQuery(name = "Creator.findById", query = "SELECT c FROM Creator c WHERE c.id = :id")
    , @NamedQuery(name = "Creator.findByName", query = "SELECT c FROM Creator c WHERE c.name = :name")
    , @NamedQuery(name = "Creator.findByRole", query = "SELECT c FROM Creator c WHERE c.role = :role")
    , @NamedQuery(name = "Creator.findByComicId", query = "SELECT c FROM Creator c WHERE c.comicId = :comicId")
    , @NamedQuery(name = "Creator.findByAll", query = "SELECT c FROM Creator c WHERE c.comicId = :comicId AND c.role = :role AND  c.name = :name")})
public class Creator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "role")
    private String role;
    @JoinColumn(name = "comic_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Comic comicId;

    public Creator() {
    }

    public Creator(Integer id) {
        this.id = id;
    }

    public Creator(Item creator, Comic comicId) {
        this.name = creator.getName();
        this.role = creator.getRole();
        this.comicId = comicId;
    }

    public Creator load(Creator creator) {
        this.name = creator.getName();
        this.role = creator.getRole();
        return this;
    }

    // <editor-fold defaultstate="collapsed" desc="Main getters and setters. Click on the + sign on the left to edit the code.">
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Comic getComicId() {
        return comicId;
    }

    public void setComicId(Comic comicId) {
        this.comicId = comicId;
    }
    // </editor-fold>

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Creator)) {
            return false;
        }
        Creator other = (Creator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Creator{"
                + "id=" + id + ", "
                + "name=" + name + ", "
                + "role=" + role + ", "
                + "comicId=[" + ((comicId == null) ? "NULL" : (comicId.getId() + "-" + comicId.getMarvelId())) + "]}";
    }

}
