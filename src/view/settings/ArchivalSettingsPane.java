package view.settings;

import controller.settings.ArchivalSettingsController;
import handler.ConfigHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;

public class ArchivalSettingsPane extends VBox {
    /** The label to define the Archival Settings section.. */
    private final Label label_paneName = new Label("Archival Settings");

    /** The text field for the absolute path to the 7zip, or whichever compresssion program the user wants to use, executable. */
    @Getter private final TextField field_compressionProgramPath = new TextField();
    /** The button to open the file selection dialog for the 7zip, or whichever compression program the user wants to use, executable. */
    @Getter private final Button button_selectFile_compressionProgramPath = new Button("Select File");

    /** The text field for the base commands to use when compressing a file before encoding. */
    @Getter private final TextField field_compressionCommands = new TextField();

    /** The text field for the extension to use when outputting an archive. */
    @Getter private final TextField field_archiveOutputExtension = new TextField();

    public ArchivalSettingsPane(final Stage settingsStage, final ArchivalSettingsController controller, final ConfigHandler configHandler) {
        // Set Field Prompt Text:
        field_compressionProgramPath.setPromptText("Archive Executable Path");
        field_compressionCommands.setPromptText("Archive Commandline Parameters");
        field_archiveOutputExtension.setPromptText("Archive Output Extension");

        // Set Default Values:
        field_compressionProgramPath.setText(configHandler.getCompressionProgramPath());
        field_compressionCommands.setText(configHandler.getCompressionCommands());
        field_archiveOutputExtension.setText(configHandler.getCompressionOutputExtension());

        // Set Component Tooltips:
        field_compressionProgramPath.setTooltip(new Tooltip("The absolute path to 7zip/7zip.exe or whichever compression program is specified."));
        button_selectFile_compressionProgramPath.setTooltip(new Tooltip("Opens the file selection dialog to locate a compression program executable."));

        field_compressionCommands.setTooltip(new Tooltip("The base commands to use when compressing a file before encoding."));

        field_archiveOutputExtension.setTooltip(new Tooltip("The extension to use when outputting an archive. If this is \"7z\" then the output is \"file.7z\"."));

        // Set Component Listeners:
        button_selectFile_compressionProgramPath.setOnAction(controller);

        // Setup the Layout:
        final HBox pane_middle = new HBox(10);
        HBox.setHgrow(field_compressionProgramPath, Priority.ALWAYS);
        pane_middle.getChildren().addAll(field_compressionProgramPath, button_selectFile_compressionProgramPath);


        HBox.setHgrow(field_compressionCommands, Priority.ALWAYS);
        this.setSpacing(4);
        this.getChildren().addAll(label_paneName, pane_middle, field_compressionCommands, field_archiveOutputExtension);
    }
}
