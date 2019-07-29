package com.feyon.myapplication.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.feyon.myapplication.R;

import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;


public class Main13Activity extends AppCompatActivity {

    private String TAG=getClass().getSimpleName();
    private Subscription subscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);

        init();
    }

    private void init(){


//        Observable observable=Observable.create(new ObservableOnSubscribe() {
//            @Override
//            public void subscribe(ObservableEmitter e) throws Exception {
//                e.onNext("1");
//            }
//        });
//
//        Observer observer=new Observer() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Object o) {
//                System.out.println(o.toString());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };

        //Consumer 只实现
//        observable.subscribe(new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//
//            }
//        });
//
//
//        observable.subscribe(observer);
//
//
//        observable.doOnNext(new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//
//            }
//        });
//
//        Observable<String> observable1=Observable.create(new ObservableOnSubscribe<Integer>() {
//
//
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//
//            }
//        }).flatMap(new Function<Integer, ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> apply(Integer integer) throws Exception {
//
//
//                return Observable.fromArray(null);
//            }
//        });

 /*       Observable<String> observable=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.i(TAG, "subscribe: 1");
                e.onNext("1");
                Thread.sleep(3000);
                Log.i(TAG, "subscribe: 2");
                e.onNext("2");
                Log.i(TAG, "subscribe: 3");
                e.onNext("3");
                Log.i(TAG, "subscribe: 4");
                e.onNext("4");

            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable1=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.i(TAG, "subscribe: A");
                e.onNext("A");

                e.onNext("B");
//                Log.i(TAG, "subscribe: B");
//                e.onNext("C");
//                Log.i(TAG, "subscribe: C");
//                e.onComplete();
//                Log.i(TAG, "subscribe: complete");
            }
        }).subscribeOn(Schedulers.io());
*/

   /*     Observable.zip(observable, observable1, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                return "合并";
            }
        }).subscribe(new ResourceObserver<String>() {
            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Observable.timer(2,TimeUnit.HOURS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println();
            }
        });

       Disposable disposable= Observable.interval(1,TimeUnit.SECONDS).doOnNext(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println();
            }
        }).doOnNext(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {

            }
        }).doOnNext(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {

            }
        }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {

            }
        });
       disposable.dispose();

        Flowable<Integer> flowable=Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter e) throws Exception {
                for (int i = 0;i<300; i++) {

                    if (e.requested()!=0)
                    {
                        e.onNext(i);
                        Log.i(TAG, "subscribe: "+i+"  request"+e.requested() +" Thead"+Thread.currentThread().getName());
                    }

                }
            }
        }, BackpressureStrategy.ERROR);

        flowable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                subscription=s;
//                s.request(1);
            }

            @Override
            public void onNext(Integer o) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "onNext: "+o.toString()+" Thead"+Thread.currentThread().getName());
//                subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
*/
       /* final int[] i = {0};
        Observable.just("'1").doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println();
            }
        }).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {

                return objectObservable;
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {

            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
*/


      /*  final int[] i = {0};
        Observable.range(1, 3).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                i[0] =i[0]+1;
                if (i[0]==5)
                {
                    objectObservable.
                }
                return objectObservable.delay(2,TimeUnit.SECONDS);
            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

;            }

        });*/

     /*   final int[] i = {0};
        Observable.range(1, 3).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {

                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {
                        i[0] =i[0]+1;
                        if (i[0]==5)
                        {
                            return Observable.empty();
                        }else {
                            return Observable.create(new ObservableOnSubscribe<Object>() {
                                @Override
                                public void subscribe(ObservableEmitter<Object> e) throws Exception {
                                    e.onNext("1");
                                }
                            });
                        }

                    }
                });
            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

                ;            }

        });*/

   /*  Observable.just("1").repeat().subscribe(new Observer<String>() {
         @Override
         public void onSubscribe(Disposable d) {

         }

         @Override
         public void onNext(String s) {

         }

         @Override
         public void onError(Throwable e) {

         }

         @Override
         public void onComplete() {

         }
     });*/

   /*     Observable.just("1","2")
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                String name= Thread.currentThread().getName();
                Log.i(TAG, "accept1: "+s);
//                System.out.println();
//                Thread.sleep(2000);
            }})
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "accept2: "+s);
//                String name= Thread.currentThread().getName();
//                System.out.println();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .concatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Exception {
                String name= Thread.currentThread().getName();
                System.out.println();
                Log.i(TAG, "apply: ");
                return Observable.just(s+"f").subscribeOn(Schedulers.io());
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i(TAG, "subscribe: ");
//                String name= Thread.currentThread().getName();
//                System.out.println();
            }
        });*/

   /*Observable o= Observable.create(new ObservableOnSubscribe<Object>() {
       @Override
       public void subscribe(ObservableEmitter<Object> e) throws Exception {
           Thread.sleep(2000);
           e.onNext("1");
       }
   });

   Observable o2= Observable.just("2");

   Observable.zip(o, o2, new BiFunction() {
       @Override
       public Object apply(Object o, Object o2) throws Exception {
           return null;
       }
   }).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
       @Override
       public void accept(Object o) throws Exception {

       }
   });
*/

 /*  Observable observable2= Observable.create(new ObservableOnSubscribe<Object>() {
       @Override
       public void subscribe(ObservableEmitter<Object> e) throws Exception {
           Thread.sleep(2000);
           e.onNext("1");
       }
   });*/

  /*  Observable.just("1","3","2","3","4").filter(new Predicate<String>() {
       @Override
       public boolean test(String s) throws Exception {
           return true;
       }
   }).distinct(new Function<String, Object>() {
       @Override
       public Object apply(String s) throws Exception {
               return null;

       }
   }).subscribe(new Consumer<String>() {
        @Override
        public void accept(String s) throws Exception {

        }
    });*/



//   Observable.merge(o2,observable1).repeatUntil(new BooleanSupplier() {
//       @Override
//       public boolean getAsBoolean() throws Exception {
//           return false;
//       }
//   }).buffer().subscribe(new Consumer() {
//       @Override
//       public void accept(Object o) throws Exception {
//
//       }
//   });

//        Observable.interval(1,TimeUnit.SECONDS).skip(4,TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//
//            }
//        });

       /* Observable.just("1","2","3","3","3","3","3").take(4).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });*/

      /* Observable.interval(1,TimeUnit.SECONDS).throttleLast(2,TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
           @Override
           public void accept(Long aLong) throws Exception {
               System.out.println();
               Log.i("throttleLast", "accept: "+aLong);
           }
       });*/





//        asyncSubject.onNext("2");
      /* Observable observable2= Observable.create(new ObservableOnSubscribe<Object>() {
           @Override
           public void subscribe(ObservableEmitter<Object> e) throws Exception {
               e.onNext("1");
               e.onNext("2");repeatWhen
           }
       }).debounce(5,TimeUnit.SECONDS);
       observable2.subscribe(new Consumer() {
           @Override
           public void accept(Object o) throws Exception {

           }
       });*/

     /* Observable.create(new ObservableOnSubscribe<Object>() {
          @Override
          public void subscribe(ObservableEmitter<Object> e) throws Exception {

          }
      }).concatMap(new Function<Object, ObservableSource<?>>() {
          @Override
          public ObservableSource<?> apply(Object o) throws Exception {
              return null;
          }
      });

        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

       /* Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                e.onNext("1");
                e.onError(new Throwable(""));
            }
        }).retry(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Exception {
                return false;
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return null;
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/
        final PublishSubject publishSubject=PublishSubject.create();
        EditText editText=findViewById(R.id.edit);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                publishSubject.onNext(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        publishSubject.subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG, "onNext: "+o.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
