package milkman.plugin.jdbc.editor;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;
import milkman.domain.RequestContainer;
import milkman.plugin.jdbc.domain.JdbcRequestContainer;
import milkman.ui.plugin.RequestTypeEditor;
import milkman.utils.fxml.FxmlUtil;
import milkman.utils.fxml.GenericBinding;

public class JdbcRequestEditor implements RequestTypeEditor {

	
	@FXML TextField jdbcUrl;
	
	private GenericBinding<JdbcRequestContainer, String> urlBinding = GenericBinding.of(JdbcRequestContainer::getJdbcUrl, JdbcRequestContainer::setJdbcUrl);
	
	
	@Override
	@SneakyThrows
	public Node getRoot() {
		Node root = FxmlUtil.loadAndInitialize("/jdbc/MainEditorArea.fxml", this);
		return root;
	}

	@Override
	public void displayRequest(RequestContainer request) {
		if (!(request instanceof JdbcRequestContainer))
			throw new IllegalArgumentException("Other request types not yet supported");
		
		JdbcRequestContainer restRequest = (JdbcRequestContainer)request;
		
		urlBinding.bindTo(jdbcUrl.textProperty(), restRequest);
		urlBinding.addListener(s -> request.setDirty(true));
		
	}


}