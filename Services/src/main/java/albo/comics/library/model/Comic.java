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

import com.albo.library.json.comics.Result;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author jacito
 */
@Entity
@Table(name = "comic")
@NamedQueries({
    @NamedQuery(name = "Comic.findAll", query = "SELECT c FROM Comic c")
    , @NamedQuery(name = "Comic.findById", query = "SELECT c FROM Comic c WHERE c.id = :id")
    , @NamedQuery(name = "Comic.findByMarvelId", query = "SELECT c FROM Comic c WHERE c.marvelId = :marvelId")
    , @NamedQuery(name = "Comic.findByMarvelTitle", query = "SELECT c FROM Comic c WHERE c.marvelTitle = :marvelTitle")
    , @NamedQuery(name = "Comic.findByMarvelDescription", query = "SELECT c FROM Comic c WHERE c.marvelDescription = :marvelDescription")})
public class Comic implements Serializable {

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
    @Column(name = "marvel_title")
    private String marvelTitle;
    @Column(name = "marvel_description")
    private String marvelDescription;
    @Column(name = "marvel_date_modified")
    private String marvelDateModified;

    @JoinColumn(name = "character_principal_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CharacterPrincipal characterPrincipalId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comicId")
    private List<Creator> creatorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comicId")
    private List<CharactersRelationship> charactersRelationshipList;

    @Transient
    private Result result;

    public Comic() {
    }

    public Comic(Integer id) {
        this.id = id;
    }

    public Comic(Integer id, int marvelId, String marvelTitle) {
        this.id = id;
        this.marvelId = marvelId;
        this.marvelTitle = marvelTitle;
    }

    public Comic(CharacterPrincipal characterPrincipalId, Result result) {
        this.characterPrincipalId = characterPrincipalId;
        this.marvelId = result.getId();
        this.marvelTitle = result.getTitle();
        this.marvelDescription = result.getDescription();
        this.marvelDateModified = result.getModified();

        this.creatorList = new ArrayList<>();
        this.charactersRelationshipList = new ArrayList<>();
        this.result = result;
    }

    public Comic load(Comic comic) {
        this.marvelId = comic.getMarvelId();
        this.marvelTitle = comic.getMarvelTitle();
        this.marvelDescription = comic.getMarvelDescription();
        this.marvelDateModified = comic.getMarvelDateModified();
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMarvelId() {
        return marvelId;
    }

    public void setMarvelId(int marvelId) {
        this.marvelId = marvelId;
    }

    public String getMarvelTitle() {
        return marvelTitle;
    }

    public void setMarvelTitle(String marvelTitle) {
        this.marvelTitle = marvelTitle;
    }

    public String getMarvelDescription() {
        return marvelDescription;
    }

    public void setMarvelDescription(String marvelDescription) {
        this.marvelDescription = marvelDescription;
    }

    public CharacterPrincipal getCharacterPrincipalId() {
        return characterPrincipalId;
    }

    public void setCharacterPrincipalId(CharacterPrincipal characterPrincipalId) {
        this.characterPrincipalId = characterPrincipalId;
    }

    public String getMarvelDateModified() {
        return marvelDateModified;
    }

    public void setMarvelDateModified(String marvelDateModified) {
        this.marvelDateModified = marvelDateModified;
    }

    public List<Creator> getCreatorList() {
        return creatorList;
    }

    public void setCreatorList(List<Creator> creatorList) {
        this.creatorList = creatorList;
    }

    public List<CharactersRelationship> getCharactersRelationshipList() {
        return charactersRelationshipList;
    }

    public void setCharactersRelationshipList(List<CharactersRelationship> charactersRelationshipList) {
        this.charactersRelationshipList = charactersRelationshipList;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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
        if (!(object instanceof Comic)) {
            return false;
        }
        Comic other = (Comic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Comic{"
                + "id=" + id + ", "
                + "marvelId=" + marvelId + ", "
                + "marvelTitle=" + marvelTitle + ", "
                + "marvelDescription=" + marvelDescription + ", "
                + "marvelDateModified=" + marvelDateModified + ", "
                + "characterPrincipalId=[" + ((characterPrincipalId == null)
                        ? "NULL" : (characterPrincipalId.getId() + "- " + characterPrincipalId.getName())) + "]}";
    }

}
