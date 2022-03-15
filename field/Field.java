package field;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import shared.DB;

public class Field {
    private final SimpleStringProperty fieldID;
    private final SimpleStringProperty fieldName;
    private final SimpleBooleanProperty canDelete = new SimpleBooleanProperty(true);

    public Field(String fieldID, String fieldName) {
        this.fieldID = new SimpleStringProperty(fieldID);
        this.fieldName = new SimpleStringProperty(fieldName);
    }

    public Field(String fieldName) {
        this.fieldID = new SimpleStringProperty("-1");
        this.fieldName = new SimpleStringProperty(fieldName);
    }

    public String getFieldID() {
        return fieldID.get();
    }

    public String getFieldName() {
        return fieldName.get();
    }

    public boolean getCanDelete() {
        return canDelete.get();
    }

    public void setCanEdit(boolean canDelete) {
        this.canDelete.set(canDelete);
    }

    public void setFieldID(String fieldID) {
        this.fieldID.set(fieldID);
    }

    public void setFieldName(String fieldName) {
        this.fieldName.set(fieldName);
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete.set(canDelete);
    }

    public void update() {
        System.out.println("Updating field");
        DB.updateField(this);
    }

    public void delete() {
        System.out.println("Deleting field");
        DB.deleteField(this.getFieldID());
    }

    public String toString() {
        if (fieldID.get() == null) { 
            return "";
        }
        else {
            return fieldName.get();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Field other = (Field) obj;
        if (fieldID == null) {
            if (other.fieldID != null)
                return false;
        } else if (!fieldID.equals(other.fieldID))
            return false;
        return true;
    }
}
