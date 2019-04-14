package com.imaginea.socialnetwork.data;
import java.util.function.Predicate;

public final class PredicateContainer {

	public final static Predicate<String> STRING_EMPTY_CHECK_PREDICATE = (String s) -> null == s || s.trim().isEmpty();

	public Predicate<String> stringEmptyCheckPredicate() {
		return STRING_EMPTY_CHECK_PREDICATE;
	}
}



	

