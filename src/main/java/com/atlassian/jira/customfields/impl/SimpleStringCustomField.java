package com.atlassian.jira.customfields.impl;

import com.atlassian.jira.customfields.api.IBalancedParenthesisCustomField;
import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

@ExportAsService({IBalancedParenthesisCustomField.class})
@Scanned
public class SimpleStringCustomField extends AbstractSingleFieldType<String> implements IBalancedParenthesisCustomField{
//    private static final Logger log = LoggerFactory.getLogger(SimpleStringCustomField.class);

    @ComponentImport
    private final CustomFieldValuePersister customFieldValuePersister;

    @ComponentImport
    private final GenericConfigManager genericConfigManager;


    @Inject
    public SimpleStringCustomField(CustomFieldValuePersister customFieldValuePersister,
                                   GenericConfigManager genericConfigManager) {
        super(customFieldValuePersister, genericConfigManager);

        this.customFieldValuePersister = customFieldValuePersister;
        this.genericConfigManager = genericConfigManager;
    }

    @Nonnull
    @Override
    protected PersistenceFieldType getDatabaseType() {
        return PersistenceFieldType.TYPE_LIMITED_TEXT;
    }

    @Nullable
    @Override
    protected Object getDbValueFromObject(String s) {
        return getStringFromSingularObject(s);
    }

    @Nullable
    @Override
    protected String getObjectFromDbValue(@Nonnull Object o) throws FieldValidationException {
        return getSingularObjectFromString((String) o);
    }

    @Override
    public String getStringFromSingularObject(String singularObject) {
        return singularObject;
    }

    @Override
    public String getSingularObjectFromString(String string) throws FieldValidationException {
        if (string == null) return null; // do not check if a string is empty

        boolean isBalanced = BalancedParenthesis.isBalancedParenthesis(string);

        if (!isBalanced) throw new FieldValidationException("Parenthesis is un-balanced");
        return string;

    }
}