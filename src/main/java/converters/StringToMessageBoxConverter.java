
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.MessageBoxService;
import domain.MessageBox;

@Transactional
@Component
public class StringToMessageBoxConverter implements Converter<String, MessageBox> {

	@Autowired
	private MessageBoxService	messageBoxService;


	@Override
	public MessageBox convert(String text) {
		MessageBox res;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				res = null;
			} else {
				id = Integer.valueOf(text);
				res = this.messageBoxService.findById(id);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return res;
	}

}
