
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.CategoryService;
import domain.Category;

@Transactional
@Component
public class StringToCategoryConverter implements Converter<String, Category> {

	@Autowired
	private CategoryService	categoryService;


	@Override
	public Category convert(String text) {
		Category res;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				res = null;
			} else {
				id = Integer.valueOf(text);
				res = this.categoryService.findById(id);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return res;
	}

}
