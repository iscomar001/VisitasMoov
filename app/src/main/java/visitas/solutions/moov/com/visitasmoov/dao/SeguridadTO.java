package visitas.solutions.moov.com.visitasmoov.dao;

import com.omr.solutions.utils.sqllite.ColType;
import com.omr.solutions.utils.sqllite.Table;
import com.omr.solutions.utils.sqllite.TableType;

/**
 * Created by OmarAlberto on 7/29/2015.
 */
public class SeguridadTO extends Table {

    @TableType(tableName="tblUsuarios")
    @ColType(colName = "id", acceptNull = false,descriptionName = "ID" , isAutoIncrement = true,isPrimary = true, colType = SQL_TYPE_INTEGER)
    private int id = -1;
    @ColType(colName = "usuario", acceptNull = false,descriptionName = "USUARIO" , colType = SQL_TYPE_TEXT)
    private String usuario;
    @ColType(colName = "password", acceptNull = false,descriptionName = "PASSWORD" , colType = SQL_TYPE_TEXT)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
