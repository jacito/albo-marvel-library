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

import com.albo.library.json.characters.Result;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jacito
 */
@Entity
@Table(name = "character_marvel")
@NamedQueries({
    @NamedQuery(name = "CharacterMarvel.findAll", query = "SELECT c FROM CharacterMarvel c")
    , @NamedQuery(name = "CharacterMarvel.findById", query = "SELECT c FROM CharacterMarvel c WHERE c.id = :id")
    , @NamedQuery(name = "CharacterMarvel.findByCharacterPrincipalId", query = "SELECT c FROM CharacterMarvel c WHERE c.characterPrincipalId = :characterPrincipalId")
    , @NamedQuery(name = "CharacterMarvel.findByMarvelId", query = "SELECT c FROM CharacterMarvel c WHERE c.marvelId = :marvelId")
    , @NamedQuery(name = "CharacterMarvel.findByMarvelName", query = "SELECT c FROM CharacterMarvel c WHERE c.marvelName = :marvelName")
    , @NamedQuery(name = "CharacterMarvel.findByMarvelDateModified", query = "SELECT c FROM CharacterMarvel c WHERE c.marvelDateModified = :marvelDateModified")
    , @NamedQuery(name = "CharacterMarvel.findByMarvelDescription", query = "SELECT c FROM CharacterMarvel c WHERE c.marvelDescription = :marvelDescription")})
public class CharacterMarvel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "marvel_id")
    private int marvelId;
    @Basic(optional = false)
    @Column(name = "marvel_name")
    private String marvelName;
    @Column(name = "marvel_date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date marvelDateModified;
    @Column(name = "marvel_description")
    private String marvelDescription;
    @Basic(optional = false)
    @Column(name = "marvel_comics_available")
    private int marvelComicsAvailable;

    @JoinColumn(name = "character_principal_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CharacterPrincipal characterPrincipalId;

    public CharacterMarvel() {
    }

    public CharacterMarvel(Integer id) {
        this.id = id;
    }

    public CharacterMarvel(Integer id, CharacterPrincipal characterPrincipalId, int marvelId, String marvelName, Date marvelDateModified, String marvelDescription) {
        this.id = id;
        this.characterPrincipalId = characterPrincipalId;
        this.marvelId = marvelId;
        this.marvelName = marvelName;
        this.marvelDateModified = marvelDateModified;
        this.marvelDescription = marvelDescription;
    }

    public CharacterMarvel(CharacterPrincipal characterPrincipalId, Result result) {
        this.characterPrincipalId = characterPrincipalId;
        this.marvelId = result.getId();
        this.marvelName = result.getName();
        this.marvelDateModified = result.getModified();
        this.marvelDescription = result.getDescription();
        this.marvelComicsAvailable = result.getComics().getAvailable();
    }

    public CharacterMarvel load(CharacterMarvel characterMarvel) {
        this.marvelName = characterMarvel.getMarvelName();
        this.marvelDateModified = characterMarvel.getMarvelDateModified();
        this.marvelDescription = characterMarvel.getMarvelDescription();
        this.marvelComicsAvailable = characterMarvel.getMarvelComicsAvailable();
        return this;
    }

    // <editor-fold defaultstate="collapsed" desc="Main getters and setters. Click on the + sign on the left to edit the code.">
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CharacterPrincipal getCharacterPrincipalId() {
        return characterPrincipalId;
    }

    public void setCharacterPrincipalId(CharacterPrincipal characterPrincipalId) {
        this.characterPrincipalId = characterPrincipalId;
    }

    public int getMarvelId() {
        return marvelId;
    }

    public void setMarvelId(int marvelId) {
        this.marvelId = marvelId;
    }

    public String getMarvelName() {
        return marvelName;
    }

    public void setMarvelName(String marvelName) {
        this.marvelName = marvelName;
    }

    public Date getMarvelDateModified() {
        return marvelDateModified;
    }

    public void setMarvelDateModified(Date marvelDateModified) {
        this.marvelDateModified = marvelDateModified;
    }

    public String getMarvelDescription() {
        return marvelDescription;
    }

    public void setMarvelDescription(String marvelDescription) {
        this.marvelDescription = marvelDescription;
    }

    public int getMarvelComicsAvailable() {
        return marvelComicsAvailable;
    }

    public void setMarvelComicsAvailable(int marvelComicsAvailable) {
        this.marvelComicsAvailable = marvelComicsAvailable;
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
        if (!(object instanceof CharacterMarvel)) {
            return false;
        }
        CharacterMarvel other = (CharacterMarvel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CharacterMarvel{"
                + "id=" + id + ", "
                + "marvelId=" + marvelId + ", "
                + "marvelName=" + marvelName + ", "
                + "marvelDateModified=" + marvelDateModified + ", "
                + "marvelDescription=" + marvelDescription + ", "
                + "marvelComicsAvailable=" + marvelComicsAvailable + ", "
                + "characterPrincipalId=[" + ((characterPrincipalId != null)
                        ? (characterPrincipalId.getId() + "- " + characterPrincipalId.getName()) : "NULL") + "]}";
    }

}
