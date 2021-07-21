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

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jacito
 */
@Entity
@Table(name = "character_principal")
@NamedQueries({
    @NamedQuery(name = "CharacterPrincipal.findAll", query = "SELECT c FROM CharacterPrincipal c")
    , @NamedQuery(name = "CharacterPrincipal.findById", query = "SELECT c FROM CharacterPrincipal c WHERE c.id = :id")
    , @NamedQuery(name = "CharacterPrincipal.findByName", query = "SELECT c FROM CharacterPrincipal c WHERE c.name = :name")
    , @NamedQuery(name = "CharacterPrincipal.findByTemplate", query = "SELECT c FROM CharacterPrincipal c WHERE c.template = :template")
    , @NamedQuery(name = "CharacterPrincipal.findBySystemUpdateDate", query = "SELECT c FROM CharacterPrincipal c WHERE c.systemUpdateDate = :systemUpdateDate")})
public class CharacterPrincipal implements Serializable {

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
    @Column(name = "marvel_name")
    private String marvelName;
    @Basic(optional = false)
    @Column(name = "template")
    private String template;
    @Column(name = "system_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemUpdateDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "characterPrincipalId")
    private List<CharactersRelationship> charactersRelationshipList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "characterPrincipalId")
    private List<Comic> comicList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "characterPrincipalId")
    private List<CharacterMarvel> characterMarvelList;

    public CharacterPrincipal() {
    }

    public CharacterPrincipal(Integer id) {
        this.id = id;
    }

    public CharacterPrincipal(Integer id, String name, String template) {
        this.id = id;
        this.name = name;
        this.template = template;
    }

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

    public String getMarvelName() {
        return marvelName;
    }

    public void setMarvelName(String marvelName) {
        this.marvelName = marvelName;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Date getSystemUpdateDate() {
        return systemUpdateDate;
    }

    public void setSystemUpdateDate(Date systemUpdateDate) {
        this.systemUpdateDate = systemUpdateDate;
    }

    public List<CharactersRelationship> getCharactersRelationshipList() {
        return charactersRelationshipList;
    }

    public void setCharactersRelationshipList(List<CharactersRelationship> charactersRelationshipList) {
        this.charactersRelationshipList = charactersRelationshipList;
    }

    public List<Comic> getComicList() {
        return comicList;
    }

    public void setComicList(List<Comic> comicList) {
        this.comicList = comicList;
    }

    public List<CharacterMarvel> getCharacterMarvelList() {
        return characterMarvelList;
    }

    public void setCharacterMarvelList(List<CharacterMarvel> characterMarvelList) {
        this.characterMarvelList = characterMarvelList;
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
        if (!(object instanceof CharacterPrincipal)) {
            return false;
        }
        CharacterPrincipal other = (CharacterPrincipal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CharacterPrincipal{"
                + "id=" + id + ", "
                + "name=" + name + ", "
                + "marvelName=" + marvelName + ", "
                + "template=" + template + ", "
                + "systemUpdateDate=" + systemUpdateDate + '}';
    }

}
