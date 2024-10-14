package ku.cs.controllers.components.navigationbars;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import ku.cs.controllers.components.ProfilePictureController;
import ku.cs.services.AlertService;
import ku.cs.services.FXRouter;
import ku.cs.services.Session;

import java.io.IOException;

public class AdminNavigationBarController {
    @FXML AnchorPane navBarAnchorPane;
    @FXML Circle profilePictureCircle;
    @FXML MenuButton fontSizeMenuButton;
    @FXML MenuButton fontStyleMenuButton;
    @FXML ImageView logoutIcon;
    @FXML ImageView themeIcon;

    IconController iconThemeController;

    public void initialize() {
        ProfilePictureController.setImageToCircle(profilePictureCircle, Session.getSession().getLoggedInUser().getProfilePictureFileName());
        iconThemeController = new IconController(logoutIcon, themeIcon, fontSizeMenuButton, fontStyleMenuButton);
        iconThemeController.updateNavBar();
    }

    @FXML
    public void onPersonalInformationManagementButton() {
        try {
            FXRouter.goTo("user-personal-information-management");
        } catch (IOException e) {
            AlertService.showError("ไฟล์โปรแกรมไม่สมบูรณ์ กรุณาตรวจสอบไฟล์โปรแกรม");
            System.exit(1);
        }
    }

    @FXML
    public void onAdminUserManagementButton() {
        try {
            FXRouter.goTo("admin-user-management");
        } catch (IOException e) {
            AlertService.showError("ไฟล์โปรแกรมไม่สมบูรณ์ กรุณาตรวจสอบไฟล์โปรแกรม");
            System.exit(1);
        }
    }

    @FXML
    public void onFacultyManagementMenuItem() {

        try {
            FXRouter.goTo("admin-faculty-management");
        } catch (IOException e) {
            AlertService.showError("ไฟล์โปรแกรมไม่สมบูรณ์ กรุณาตรวจสอบไฟล์โปรแกรม");
            System.exit(1);
        }
    }

    @FXML
    public void onDepartmentManagementMenuItem() {

        try {
            FXRouter.goTo("admin-department-management");
        } catch (IOException e) {
            AlertService.showError("ไฟล์โปรแกรมไม่สมบูรณ์ กรุณาตรวจสอบไฟล์โปรแกรม");
            System.exit(1);
        }
    }

    @FXML
    public void onFacultyOfficerManagementMenuItem() {

        try {
            FXRouter.goTo("admin-faculty-officer-management");
        } catch (IOException e) {
            AlertService.showError("ไฟล์โปรแกรมไม่สมบูรณ์ กรุณาตรวจสอบไฟล์โปรแกรม");
            System.exit(1);
        }
    }

    @FXML
    public void onDepartmentOfficerManagementMenuItem() {

        try {
            FXRouter.goTo("admin-department-officer-management");
        } catch (IOException e) {
            AlertService.showError("ไฟล์โปรแกรมไม่สมบูรณ์ กรุณาตรวจสอบไฟล์โปรแกรม");
            System.exit(1);
        }
    }

    @FXML
    public void onAdvisorManagementMenuItem() {

        try {
            FXRouter.goTo("admin-advisor-management");
        } catch (IOException e) {
            AlertService.showError("ไฟล์โปรแกรมไม่สมบูรณ์ กรุณาตรวจสอบไฟล์โปรแกรม");
            System.exit(1);
        }
    }

    @FXML
    public void onDashboardButton() {
        try {
            FXRouter.goTo("admin-dashboard");
        } catch (IOException e) {
            AlertService.showError("ไฟล์โปรแกรมไม่สมบูรณ์ กรุณาตรวจสอบไฟล์โปรแกรม");
            System.exit(1);
        }
    }

    @FXML
    public void onThemeButton() {
        Scene scene = navBarAnchorPane.getScene();
        Parent root = scene.getRoot();
        Session.getSession().getThemeProvider().changeTheme(root);
        iconThemeController.updateNavBar();
    }

    @FXML
    public void onLogoutButton() {

        Session.getSession().clearSession();
        try {
            FXRouter.goTo("user-login");
        } catch (IOException e) {
            AlertService.showError("ไฟล์โปรแกรมไม่สมบูรณ์ กรุณาตรวจสอบไฟล์โปรแกรม");
            System.exit(1);
        }
    }

    @FXML
    public void onLargeMenuItem() {
        Scene scene = navBarAnchorPane.getScene();
        Parent root = scene.getRoot();
        Session.getSession().getThemeProvider().changeFontSize(root, "Large");
    }

    @FXML
    public void onSmallMenuItem() {
        Scene scene = navBarAnchorPane.getScene();
        Parent root = scene.getRoot();
        Session.getSession().getThemeProvider().changeFontSize(root, "Small");
    }

    @FXML
    public void onMediumMenuItem() {
        Scene scene = navBarAnchorPane.getScene();
        Parent root = scene.getRoot();
        Session.getSession().getThemeProvider().changeFontSize(root, "Medium");
    }

    @FXML
    public void onSarabunMenuItem() {
        Scene scene = navBarAnchorPane.getScene();
        Parent root = scene.getRoot();
        Session.getSession().getThemeProvider().changeFontStyle(root, "Sarabun");
    }

    @FXML
    public void onMaliGradeMenuItemClick() {
        Scene scene = navBarAnchorPane.getScene();
        Parent root = scene.getRoot();
        Session.getSession().getThemeProvider().changeFontStyle(root, "Maligrade");
    }

    @FXML
    public void onAngsanaNewMenuItem() {
        Scene scene = navBarAnchorPane.getScene();
        Parent root = scene.getRoot();
        Session.getSession().getThemeProvider().changeFontStyle(root, "Angsananew");
    }


}
