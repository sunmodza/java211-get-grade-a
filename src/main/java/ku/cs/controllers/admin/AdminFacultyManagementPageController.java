package ku.cs.controllers.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import ku.cs.controllers.components.SearchController;
import ku.cs.controllers.components.tables.EventCallback;
import ku.cs.controllers.components.tables.TableComponentController;
import ku.cs.models.Faculty;
import ku.cs.models.collections.FacultyList;
import ku.cs.models.users.Admin;
import ku.cs.services.AlertService;
import ku.cs.services.Session;
import ku.cs.services.SortDirection;
import ku.cs.services.popup.PopupComponent;

import java.io.IOException;

public class AdminFacultyManagementPageController {
    @FXML private Pane navBarPane;
    @FXML private Pane tablePane;
    @FXML private TextField searchTextField;
    @FXML private Circle searchButton;
    @FXML private AnchorPane anchorPane;
    private Admin admin;
    TableComponentController<Faculty> tableController;

    SearchController searchController;

    @FXML
    public void initialize() {
        Session.getSession().setNavbar(navBarPane);
        Session.getSession().getTheme().setTheme(anchorPane);
        admin = (Admin) Session.getSession().getLoggedInUser();

        Image searchIcon = new Image(getClass().getResource("/images/search-icon.png").toString(), false);
        searchButton.setFill(new ImagePattern(searchIcon));

        tablePane.getChildren().clear();
        tablePane.getStylesheets().add(getClass().getResource("/ku/cs/views/styles/main-style.css").toString());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ku/cs/views/components/table-component.fxml"));


        FacultyList facultyList = admin.getFacultyList();

        try {
            AnchorPane table = fxmlLoader.load();
            tableController = fxmlLoader.getController();
            tableController.setHeadHeight(40);
            tableController.setRowHeight(40);
            tableController.setDisplayRowCount(8);

            tableController.setTableHeadDescriptor(new FacultyTableDescriptor());
            tableController.setTablePane(tablePane);
            tableController.sortBy("รหัสคณะ", SortDirection.ASCENDING);
            tableController.setDisplayModels(facultyList.getFaculties());

            tablePane.getChildren().add(table);
            searchController = new SearchController(searchTextField, tableController, admin.getFacultyList());

        } catch (IOException e) {
            AlertService.showError("ระบบมีความผิดพลาด กรุณาตรวจสอบไฟล์โปรแกรม");
            System.exit(0);
        }
    }

    public void onSearchButtonClick(){
        searchController.searchFilter();
    }

    @FXML
    public void onAddFacultyButton() {
        PopupComponent<Faculty> requestActionPopup = new PopupComponent<>(null, "/ku/cs/views/admin/admin-faculty-management-popup.fxml","admin-faculty-management-popup",navBarPane.getScene().getWindow());
        requestActionPopup.show();
        requestActionPopup.getPopupController().addEventListener(
                "success", new EventCallback() {
                    @Override
                    public void onEvent(Object eventData) {
                        try {
                            tableController.setDisplayModels(admin.getFacultyList().getFaculties());
                        } catch (IOException e) {
                            AlertService.showError("ระบบมีความผิดพลาด กรุณาตรวจสอบไฟล์โปรแกรม");
                            System.exit(0);
                        }
                        searchController.searchFilter();
                    }
                }
        );
    }
}
