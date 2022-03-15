package field;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import shared.DB;
import shared.TableColumnFactory;
import shared.EditingCell;

public class FieldView {
    TableColumnFactory<Field, String> columnFactory = new TableColumnFactory<>();

    TableView<Field> table = new TableView<Field>();
    final ObservableList<Field> data = FXCollections.observableArrayList();

    final Callback<TableColumn<Field, String>, TableCell<Field, String>> cellFactory = new Callback<TableColumn<Field, String>, TableCell<Field, String>>() {
        public TableCell<Field, String> call(TableColumn<Field, String> p) {
            return new EditingCell<Field, String>();
        }
    };

    public FieldView() {
    }

    public void reloadFields() {
        data.clear();
        data.addAll(DB.loadFields());
    }

    public Node getFieldTab() throws SQLException {
        reloadFields();

        final HBox hb = new HBox();

        final Label label = new Label("Field");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        //Create Columns
        TableColumn<Field, String> fieldIDCol = columnFactory.getTableColumn("fieldID", "Field ID", false, 100,
                cellFactory);
        TableColumn<Field, String> fieldNameCol = columnFactory.getTableColumn("fieldName", "Field Name", true,
                100,
                cellFactory);

        // Insert Button
        TableColumn<Field, Boolean> col_action = columnFactory.getActionColumn();

        //add columns
        table.getColumns().addAll(fieldIDCol, fieldNameCol, col_action);
        table.setItems(data);

        final TextField addFieldName = new TextField();
        addFieldName.setMaxWidth(addFieldName.getPrefWidth());
        addFieldName.setPromptText("Field Name");

        final Button addButton = new Button("Add");
        addButton.setOnAction(e -> {

            DB.insertField(new Field(addFieldName.getText()));
            reloadFields();

            addFieldName.clear();
        });

        hb.getChildren().addAll(addFieldName, addButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        
        return vbox;
    }

}