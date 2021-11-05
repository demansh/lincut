package com.dem.lincut.web;

import com.dem.lincut.core.model.ShortLink;
import com.dem.lincut.core.model.ShortLinkService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
public class MainView extends VerticalLayout {
    private final ShortLinkService shortLinkService;

    @Autowired
    public MainView(ShortLinkService shortLinkService) {
        this.shortLinkService = shortLinkService;

        FormLayout shortLinkFormLayout = new FormLayout();
        Binder<ShortLink> binder = new Binder<>();

        TextField urlInputField = new TextField();
        urlInputField.setPlaceholder("https://google.com");
        binder.forField(urlInputField)
                .withValidator(value -> shortLinkService.validateUrl(value) != null, "Invalid url")
                .bind(ShortLink::getUrl, ShortLink::setUrl);
        Label infoLabel = new Label();
        NativeButton saveButton = new NativeButton("Save");

        HorizontalLayout actions = new HorizontalLayout();
        actions.add(saveButton);

        shortLinkFormLayout.addFormItem(urlInputField, "Url");

        saveButton.addClickListener(event -> {
            ShortLink shortLink = shortLinkService.createLink(urlInputField.getValue());
            infoLabel.setText("http://localhost:9999/" + shortLink.getToken());
        });

        add(shortLinkFormLayout, actions, infoLabel);
    }
}

