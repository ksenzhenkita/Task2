package sumdu.edu.ua;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "sumdu.edu.ua")
public class ArchitectureTest {

    @ArchTest
    static final ArchRule layers_are_respected = layeredArchitecture()
            .consideringAllDependencies()
            // Тепер Web — це і сервлети, і конфігурація
            .layer("Web").definedBy("..servlet..", "..config..", "..controller..")
            .layer("Persistence").definedBy("..repository..")
            .layer("Core").definedBy("..service..", "..model..", "..port..")

            .whereLayer("Web").mayNotBeAccessedByAnyLayer()
            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Web")
            .whereLayer("Core").mayOnlyBeAccessedByLayers("Web", "Persistence");
}