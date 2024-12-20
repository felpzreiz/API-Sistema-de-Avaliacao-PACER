module org.example.paceralphacode {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;

    opens org.alphacode.pacer to javafx.fxml;
    exports org.alphacode.pacer;
    exports conexao;
    opens conexao to javafx.fxml;
    exports org.alphacode.pacer.home;
    opens org.alphacode.pacer.home to javafx.fxml;
    exports org.alphacode.pacer.alunos;
    opens org.alphacode.pacer.alunos to javafx.fxml;
    exports org.alphacode.pacer.grupos;
    opens org.alphacode.pacer.grupos to javafx.fxml;
    exports org.alphacode.pacer.sprintsCriterios;
    opens org.alphacode.pacer.sprintsCriterios to javafx.fxml;
    exports org.alphacode.pacer.password;
    opens org.alphacode.pacer.password to javafx.fxml;
    opens org.alphacode.pacer.alunoacess to javafx.fxml;
    exports org.alphacode.pacer.alunoacess;

}