package br.com.trendsoftware.restProvider.exception;

public interface MessageException
{
	String GENERAL_ERROR = "GENERAL_ERROR";
	String BODY_RESPONSE_ERROR = "BODY_RESPONSE_ERROR";
	String ERROR_QUERY_USER_INFO = "Error querying user information";
	String ERROR_QUERY_ORDER = "Error querying order";
	String ERROR_QUERY_ORDER_MESSAGES = "Error querying order messages";
	String ERROR_QUERY_SHIPPING_INFO = "Error querying shipping information";
	String ERROR_QUERY_USER_ITENS = "Error querying user itens";
	String ERROR_QUERY_USER_ITEM = "Error querying user item";
	String ERROR_ADD_ITEM = "Error adding item";
	String ERROR_UPDATE_ITEM = "Error updating item";
	String ERROR_CREATE_ORDER = "Error creating order";
	String ERROR_CANCEL_ORDER = "Error canceling order";
	String ERROR_INVOICE_ORDER = "Error invoicing order";
	String ERROR_APPROVE_ORDER = "Error approving order";
	
	String ERROR_UNSUBSCRIBE = "Error unsubscribing";
	String ERROR_QUERY_SUBSCRIPTION = "Error querying subscription";
	String ERROR_EDIT_SUBSCRIPTION = "Error editting subscription";
	String ERROR_QUERY_CATALOG = "Error querying catalog";
	String ERROR_QUERY_SINGER = "Error querying singer";
	String ERROR_QUERY_CATALOG_TONE = "Error querying catalog tones";
	String ERROR_QUERY_TONE = "Error querying tone";
	String ERROR_SEND_SMS = "Error sending sms";	
	String ERROR_ADD_TONE = "Error adding tone";
	String ERROR_DEL_TONE= "Error deleting tone";	
	String ERROR_ADD_TONE_BOX = "Error adding tone box";
	String ERROR_SET_TONE_BOX = "Error setting tone box for user";	
	String ERROR_ORDER_TONE = "Error ordering tone";	
	String ERROR_QUERY_TONE_BOX = "Error querying tone box";
	String ERROR_QUERY_TONE_BOX_TONES = "Error querying tone box tones";
	String ERROR_QUERY_USER_CALLER_GROUPS = "Error querying user caller groups";
	String ERROR_QUERY_CALLER_GROUP = "Error querying caller group";
	String ERROR_QUERY_CALLER = "Error querying caller";
	String ERROR_ADD_CALLER_GROUP = "Error adding caller group";
	String ERROR_ADD_CALLER = "Error adding caller";
	String ERROR_DEL_CALLER = "Error deleting caller";
}

