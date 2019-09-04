use flatbuffers::FlatBufferBuilder;

use flatbuf::model_generated;
use model_fbs::model::*;
use model_generated as model_fbs;

#[cfg(test)]
#[test]
fn test_flatbuf() {
    let mut builder = FlatBufferBuilder::new();
    let module_args = ModuleArgs::default();
    let module = Module::create(&mut builder, &module_args);

    const MODEL_NAME: &str = "model";
    let model_name = Some(builder.create_string(MODEL_NAME));
    //    let model_module = Some(builder.create_vector_direct(module));
    let model_args = ModelArgs {
        id: 1,
        name: model_name,
        module: Option::None,
    };
    let model = Model::create(&mut builder, &model_args);
    builder.finish_minimal(model);

    let parsed = flatbuffers::get_root::<Model>(builder.finished_data());
    println!("{:?} {:?}", parsed.name(), parsed.module());
}
