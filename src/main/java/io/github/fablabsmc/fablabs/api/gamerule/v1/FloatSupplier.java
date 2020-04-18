package io.github.fablabsmc.fablabs.api.gamerule.v1;

/**
 * Represents a supplier of {@code float}-valued results.  This is the
 * {@code float}-producing primitive specialization of {@link java.util.function.Supplier}.
 */
// TODO: This probably belongs in `fabric-base-api` when merged
@FunctionalInterface
public interface FloatSupplier {
	/**
	 * Gets a result.
	 *
	 * @return a result
	 */
	float getAsFloat();
}
